package com.zhongke.weiduo.app.oss;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.zhongke.weiduo.app.ZkApplication;
import com.zhongke.weiduo.app.listener.CallBackListener;
import com.zhongke.weiduo.app.utils.LogUtil;

/**
 * Created by Karma on 2017/6/8.
 * 类描述：上传图片OSS
 */
public class ImageOSSClient {


    private String endpoint = "";
    private String accessKeyId = "";
    private String accessKeySecret = "";
    private String securityToken = "";
    private OSS oss;
    private Context context;
    private ProgressDialog pd;

    public ImageOSSClient(String endpoint, String accessKeyId, String accessKeySecret, String securityToken) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.securityToken = securityToken;
    }

    /**
     * karma
     *
     * @param endpoint
     * @param accessKeyId
     * @param accessKeySecret
     */
    public ImageOSSClient(String endpoint, String accessKeyId, String accessKeySecret) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }

    public void initialize(Context context) {
        this.context = context;
//        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(accessKeyId, accessKeySecret, securityToken);


        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节 测试阶段
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(ZkApplication.getInstance(), endpoint, credentialProvider);
    }

    public void initProgress() {
        pd = new ProgressDialog(context);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("Uploading Picture...");
        pd.setMax(100);
        pd.setCancelable(false);
        pd.show();
    }

    public void testUploadImg(String testBucket, String testObject, String uploadFilePath) {
        LogUtil.e("params = ", "   testBucket = " + testBucket + "   testObject = " + testObject + "   uploadFilePath = " + uploadFilePath);
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(testBucket, testObject, uploadFilePath);

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
//                LogUtil.e("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                int currProgress = (int) (currentSize / totalSize * 100);
                pd.setProgress(currProgress);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                pd.dismiss();
                LogUtil.e("PutObject", "UploadSuccess");

                LogUtil.e("ETag", result.getETag());
                LogUtil.e("RequestId", result.getRequestId());
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                pd.dismiss();
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    LogUtil.e("ErrorCode", serviceException.getErrorCode());
                    LogUtil.e("RequestId", serviceException.getRequestId());
                    LogUtil.e("HostId", serviceException.getHostId());
                    LogUtil.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }


    public void uploadImage(String bucketName, final String objectKey, String uploadFilePath, final int page, final CallBackListener<String> callBack) {
        LogUtil.e("params = ", "   bucketName = " + bucketName + "   objectKey = " + objectKey + "   uploadFilePath = " + uploadFilePath);

        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucketName, objectKey, uploadFilePath);

        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest putObjectRequest, long l, long l1) {
                Log.d("PutObject", "currentSize: " + l + " totalSize: " + l1);
                callBack.setProgressCallback(l, l1);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {

                LogUtil.e("PutObject", "UploadSuccess");
                LogUtil.e("ETag", result.getETag());
                LogUtil.e("RequestId", result.getRequestId());
                callBack.onSuccess(0, 0, page + "");
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {

                LogUtil.e("aaaa", "-----------> onFailure = " + serviceException.getErrorCode());
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    LogUtil.e("ErrorCode", serviceException.getErrorCode());
                    LogUtil.e("RequestId", serviceException.getRequestId());
                    LogUtil.e("HostId", serviceException.getHostId());
                    LogUtil.e("RawMessage", serviceException.getRawMessage());
                }
                callBack.onFailure(0, 0, "");
            }
        });

    }

    public void uploadImageBybyte(String bucketName, final String objectKey, byte[] loadData, final int page, final CallBackListener<String> callBack) {
        LogUtil.e("params = ", "   bucketName = " + bucketName + "   objectKey = " + objectKey);

        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucketName, objectKey, loadData);

        // 异步上传时可以设置进度回调
        put.setProgressCallback((request, currentSize, totalSize) -> {
            Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            callBack.setProgressCallback(currentSize, totalSize);
        });


        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {

                LogUtil.e("PutObject", "UploadSuccess");
                LogUtil.e("ETag", result.getETag());
                LogUtil.e("RequestId", result.getRequestId());
                callBack.onSuccess(0, 0, page + "");
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {

                LogUtil.e("aaaa", "-----------> onFailure = " + serviceException.getErrorCode());
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    LogUtil.e("ErrorCode", serviceException.getErrorCode());
                    LogUtil.e("RequestId", serviceException.getRequestId());
                    LogUtil.e("HostId", serviceException.getHostId());
                    LogUtil.e("RawMessage", serviceException.getRawMessage());
                }
                callBack.onFailure(0, 0, "");
            }
        });

    }


    public void uploadVideo(String bucketName, final String objectKey, String uploadFilePath, final CallBackListener<String> callBack) {
        LogUtil.e("params = ", "   bucketName = " + bucketName + "   objectKey = " + objectKey + "   uploadFilePath = " + uploadFilePath);

        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucketName, objectKey, uploadFilePath);
        // 异步上传时可以设置进度回调
        put.setProgressCallback((request, currentSize, totalSize) -> {
            LogUtil.e("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            callBack.setProgressCallback(currentSize, totalSize);
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
//                pd.dismiss();
                LogUtil.e("PutObject", "UploadSuccess");
                LogUtil.e("ETag", result.getETag());
                LogUtil.e("RequestId", result.getRequestId());
                callBack.onSuccess(0, 0, objectKey);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
//                pd.dismiss();
                LogUtil.e("aaaa", "-----------> onFailure = " + serviceException.getErrorCode());
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    LogUtil.e("ErrorCode", serviceException.getErrorCode());
                    LogUtil.e("RequestId", serviceException.getRequestId());
                    LogUtil.e("HostId", serviceException.getHostId());
                    LogUtil.e("RawMessage", serviceException.getRawMessage());
                }
                callBack.onFailure(0, 0, "");
            }
        });
    }

    public void synchronizationUpload(String bucketName, final String objectKey, byte[] loadData, final CallBackListener<String> callBack) {
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucketName, objectKey, loadData);
// 文件元信息的设置是可选的
// ObjectMetadata metadata = new ObjectMetadata();
// metadata.setContentType("application/octet-stream"); // 设置content-type
// metadata.setContentMD5(BinaryUtil.calculateBase64Md5(uploadFilePath)); // 校验MD5
// put.setMetadata(metadata);
        try {
            PutObjectResult putResult = oss.putObject(put);
            Log.d("PutObject", "UploadSuccess");
            Log.d("ETag", putResult.getETag());
            Log.d("RequestId", putResult.getRequestId());
        } catch (ClientException e) {
            // 本地异常如网络异常等
            e.printStackTrace();
        } catch (ServiceException e) {
            // 服务异常
            Log.e("RequestId", e.getRequestId());
            Log.e("ErrorCode", e.getErrorCode());
            Log.e("HostId", e.getHostId());
            Log.e("RawMessage", e.getRawMessage());
        }
    }

}
