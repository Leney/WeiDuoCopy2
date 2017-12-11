package com.zhongke.weiduo.library.aliyunOss;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.zhongke.weiduo.app.ZkApplication;

import org.apache.commons.lang3.text.StrBuilder;

import java.util.UUID;

/**
 * Created by ${xinGen} on 2017/11/14.
 * <p>
 * AliYun Oss SDK ：https://help.aliyun.com/document_detail/31942.html?spm=5176.doc31941.6.652.fTLfb3
 */

public class AliYunOssClient {
    private final OSS ossClient;
    private Context appContext;
    private static AliYunOssClient instance;

    private AliYunOssClient() {
        this.appContext = ZkApplication.getInstance();
        /**
         * 如果希望直接使用accessKey来访问的时候，可以直接使用OSSPlainTextAKSKCredentialProvider来鉴权。
         */
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(BuildConfig.ACCES_KEYID, BuildConfig.ACCES_KEYSECRET);
        ossClient = new OSSClient(appContext, BuildConfig.TEST_ENDPOINT, credentialProvider, createConfig());
    }

    public static synchronized AliYunOssClient getInstance() {
        if (instance == null) {
            instance = new AliYunOssClient();
        }
        return instance;
    }

    /**
     * 创建连接配置
     *
     * @return
     */
    private ClientConfiguration createConfig() {
        ClientConfiguration conf = new ClientConfiguration();
        // 连接超时，默认15秒
        conf.setConnectionTimeout(15 * 1000);
        // socket超时，默认15秒
        conf.setSocketTimeout(15 * 1000);
        // 最大并发请求书，默认5个
        conf.setMaxConcurrentRequest(5);
        // 失败后最大重试次数，默认2次
        conf.setMaxErrorRetry(2);
        return conf;
    }

    /**
     * 构建一个文件上传
     *
     * @param filePath
     * @param requestResponseListener
     * @return
     */
    public UpLoadFileRequest startUpLoad(String filePath, RequestResponseListener requestResponseListener) {
        UpLoadFileRequest request = new UpLoadFileRequest(filePath, ossClient);
        request.setRequestResponseListener(requestResponseListener);
        request.start();
        return request;
    }

    /**
     * 配置类
     */
    private final static class BuildConfig {
        /**
         * 上传文件到阿里oss地址
         */
        public static final String TEST_ENDPOINT = "http://oss-cn-shenzhen.aliyuncs.com/";
        /**
         * 阿里 KEY_ID
         */
        public static final String ACCES_KEYID = "LTAItE8B6L5hKhiC ";
        /**
         * 阿里 KEY_SECRET
         */
        public static final String ACCES_KEYSECRET = "3jPrS2SEQPdaMwcVcSQW8YB7nwi5FB";
        public static final String TEST_BUCKET = "zhongke";
        /**
         * 加载的地址
         */
        public static final String DOWNLOAD_PHOTO = "http://zhongke.oss-cn-shenzhen.aliyuncs.com/";
    }

    /**
     * 定义一个请求取消的接口
     */
    public interface RequestCancelListener {
        /**
         * 取消请求的方法
         */
        void cancel();
    }

    /**
     * 定于一个请求过程响应的接口
     */
    public interface RequestResponseListener {
        /**
         * 任务开始前的回调接口
         */
        void start();

        /**
         * 进度的回调接口
         *
         * @param progress
         */
        void onProgress(String progress);

        /**
         * 错误的回调接口
         *
         * @param localFilePath
         * @param errorMSG
         */
        void onError(String localFilePath, String errorMSG);

        /**
         * 成功的回调接口
         *
         * @param localFilePath
         * @param upLoadFilePath
         */
        void onSuccess(String localFilePath, String upLoadFilePath);
    }

    /**
     * 工具类
     */
    private static class Utils {
        /**
         * 指定PictureFile目录下存放图片
         *
         * @param filePath
         * @return
         */
        public static String getObjectKey(String filePath) {
            StrBuilder pictureUrl = new StrBuilder();
            String uuid = getUUID();
            String[] s = filePath.split("/");
            String objectKey = "PictureFile" + "/" + uuid + "/" + s[s.length - 1];
            pictureUrl.append(objectKey);
            return pictureUrl.toString();
        }

        private static String getUUID() {
            return UUID.randomUUID().toString().replaceAll("-", "");
        }

        /**
         * 计算进度
         *
         * @param currentSize
         * @param totalSize
         * @return
         */
        public static String calculateProgress(long currentSize, long totalSize) {
            //计算出上传进度
            int currProgress = (int) ((float) currentSize / totalSize * 100);
            return String.valueOf(currProgress);
        }
    }

    /**
     * 文件上传的请求
     */
    public static class UpLoadFileRequest implements RequestCancelListener {
        /**
         * 定义一个主线程的Handler
         */
        protected static final Handler mainHandler = new Handler(Looper.getMainLooper());
        private final String tag = UpLoadFileRequest.class.getSimpleName();
        /**
         * 阿里云的异步请求
         */
        private OSSAsyncTask<PutObjectResult> ossAsyncTask;
        /**
         * 本地文件路径
         */
        private String localFilePath;
        /**
         * 阿里云服务器上的图片地址
         */
        private String uploadFilePath;
        /**
         * 上传文件过程的回调接口
         */
        private RequestResponseListener requestResponseListener;
        private OSS ossClient;
        /**
         * 是否完成的标识
         */
        private boolean isFinish;

        public UpLoadFileRequest(String localFilePath, OSS ossClient) {
            this.localFilePath = localFilePath;
            this.ossClient = ossClient;
            this.isFinish =false;
        }
        /**
         * 构建一个文件上传的请求
         *
         * @return
         */
        public UpLoadFileRequest start() {
            uploadFile();
            return this;
        }
        private void uploadFile() {
            String objectKey = Utils.getObjectKey(localFilePath);
            setUploadFilePath(BuildConfig.DOWNLOAD_PHOTO + objectKey);
            /**
             * 将文件构建成OSS种Object请求
             */
            PutObjectRequest putObjectRequest = new PutObjectRequest(BuildConfig.TEST_BUCKET, objectKey, localFilePath);
            putObjectRequest.setProgressCallback((request, currentSize, totalSize) ->
                    onNext(Utils.calculateProgress(currentSize, totalSize))
            );
            //调用简单上传接口上传，回调接口是响应在异步线程。
            ossAsyncTask = ossClient.asyncPutObject(putObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                @Override
                public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
                    Log.i(tag, " onSuccess ");
                    onCompleted();
                    isFinish =true;
                }
                @Override
                public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                    Log.i(tag, " onFailure ");
                    //通过RxJava,切换到主线程
                    onError(e != null ? e : e1);
                    isFinish =true;
                }
            });
        }
        public void onCompleted() {
            Log.i(tag, " onCompleted ");
            if (requestResponseListener != null) {
                   mainHandler.post(() -> requestResponseListener.onSuccess(localFilePath, uploadFilePath));
            }
        }
        public void onError(Throwable e) {
            Log.i(tag, " onError ");
            if (requestResponseListener != null) {
                mainHandler.post(() -> requestResponseListener.onError(localFilePath, e.getMessage()));
            }
        }
        public void onNext(String progress) {
            if (requestResponseListener != null) {
                mainHandler.post(() -> requestResponseListener.onProgress(progress)
                );
            }
        }
        /**
         * 释放资源，取消订阅者和取消上传的请求
         */
        @Override
        public void cancel() {
            if (ossAsyncTask != null && !ossAsyncTask.isCanceled()) {
                ossAsyncTask.cancel();
            }
            if (requestResponseListener != null) {
                this.requestResponseListener = null;
            }
        }

        public boolean isFinish() {
            return isFinish;
        }

        private void setUploadFilePath(String uploadFilePath) {
            this.uploadFilePath = uploadFilePath;
        }
        public void setRequestResponseListener(RequestResponseListener requestResponseListener) {
            this.requestResponseListener = requestResponseListener;
        }

    }
}
