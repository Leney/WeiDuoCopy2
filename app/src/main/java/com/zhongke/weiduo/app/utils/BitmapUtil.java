package com.zhongke.weiduo.app.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by karma on 17/07/06.
 */
public class BitmapUtil {

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    // 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响
    private static Bitmap createScaleBitmap(Bitmap src, int dstWidth, int dstHeight) {
        Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
        if (src != dst) {   // 如果没有缩放，那么不回收
            src.recycle();  // 释放Bitmap的native像素数组
        }
        return dst;
    }

    // 从Resources中加载图片
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options); // 读取图片长款
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight); // 计算inSampleSize
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeResource(res, resId, options); // 载入一个稍大的缩略图
        return createScaleBitmap(src, reqWidth, reqHeight); // 进一步得到目标大小的缩略图
    }

    // 从sd卡上加载图片
    public static Bitmap decodeSampledBitmapFromFd(String pathName, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeFile(pathName, options);
        return createScaleBitmap(src, reqWidth, reqHeight);
    }

    private static final String TAG = "BitmapUtil";

    public static final int POS_START = 1;
    public static final int POS_CENTER = 2;
    public static final int POS_END = 3;

    /**
     * 根据限制参数输出图片，图片不会拉伸
     *
     * @param bmp     原始bitmap
     * @param maxOutW 输出容器的最大宽度
     * @param maxOutH 输出容器的最大高度
     * @param config  像素类型
     */
    public static Bitmap CreateBitmap(Bitmap bmp, int maxOutW, int maxOutH, Bitmap.Config config) {

        if (bmp == null) {
            return null;
        }
        int inW = bmp.getWidth();// 原图宽度
        int inH = bmp.getHeight();// 原图高度

        int outW = 0;// 实际输出宽
        int outH = 0;// 实际输出高

        float scale = 1;// 实际输出比例

        // 如果图片的宽高比大于或等于显示区域宽高，则将图片宽拉伸到与显示区同宽，再按比例拉伸图片的高
        // 反之，则将图片高拉伸到与显示区同高,再按比例拉伸图片宽
        if (((float) inW / (float) inH) >= ((float) maxOutW / (float) maxOutH)) {
            outW = maxOutW;
            scale = (float) outW / (float) inW;
            outH = (int) (inH * scale);
        } else {
            outH = maxOutH;
            scale = (float) outH / (float) inH;
            outW = (int) (inW * scale);
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale, 0, 0);// 沿0,0点缩放scale

        try {

            Bitmap outBmp = Bitmap.createBitmap(outW, outH, config);
            Canvas canvas = new Canvas(outBmp);
            canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
            canvas.drawBitmap(bmp, matrix, null);

            return outBmp;
        } catch (Exception e) {
            LogUtil.e(TAG, "", e);
        } catch (OutOfMemoryError e) {
            LogUtil.e(TAG, "", e);
            System.gc();
        }
        return null;
    }

    /**
     * 返回符合比例的图片裁剪矩形
     *
     * @param w         图片的宽
     * @param h         图片的高
     * @param scale_w_h 宽高比例
     */
    public static MyRect MakeRect(float w, float h, float scale_w_h) {
        MyRect outRect = new MyRect();
        outRect.m_w = w;
        outRect.m_h = w / scale_w_h;
        if (outRect.m_h > h) {
            outRect.m_h = h;
            outRect.m_w = h * scale_w_h;
        }
        outRect.m_x = (w - outRect.m_w) / 2f;
        outRect.m_y = (h - outRect.m_h) / 2f;

        return outRect;
    }

    public static class MyRect {
        public float m_x;
        public float m_y;
        public float m_w;
        public float m_h;
    }

    /**
     * 按比例缩放，输出大小与要求的一致（自动裁剪超出部分）
     *
     * @param bmp     原始bitmap
     * @param outW    输出的图片宽
     * @param outH    输出的图片高
     * @param posType POS_START(开始截取) POS_CENTER(中间截取) POS_END(尾部截取)
     * @param rotate  原始bitmap需要旋转的角度0,90,180,270...
     * @param config  像素类型
     */
    public static Bitmap CreateFixBitmap(Bitmap bmp, int outW, int outH, int posType, int rotate, Bitmap.Config config) {
        Bitmap outBmp = null;
        if (bmp != null) {
            int inW = bmp.getWidth();
            int inH = bmp.getHeight();

            if (rotate % 180 != 0) {
                inW += inH;
                inH = inW - inH;
                inW -= inH;
            }

            float scale = (float) outW / inW;
            if (scale * inH < outH) {
                scale = (float) outH / inH;
            }

            Matrix matrix = new Matrix();
            switch (posType) {
                case POS_START:
                    switch (rotate % 360) {
                        case 0:
                            matrix.postScale(scale, scale, 0, 0);
                            break;
                        case 90:
                            matrix.postTranslate(inW, 0);
                            matrix.postRotate(rotate, inW, 0);
                            matrix.postScale(scale, scale, 0, 0);
                            break;
                        case 180:
                            matrix.postTranslate(inW, inH);
                            matrix.postRotate(rotate, inW, inH);
                            matrix.postScale(scale, scale, 0, 0);
                            break;
                        case 270:
                            matrix.postTranslate(0, inH);
                            matrix.postRotate(rotate, 0, inH);
                            matrix.postScale(scale, scale, 0, 0);
                            break;
                        default:
                            break;
                    }
                    break;
                case POS_CENTER:
                    matrix.postTranslate((outW - bmp.getWidth()) / 2f, (outH - bmp.getHeight()) / 2f);
                    matrix.postRotate(rotate, outW / 2f, outH / 2f);
                    matrix.postScale(scale, scale, outW / 2f, outH / 2f);
                    break;
                case POS_END:
                    switch (rotate % 360) {
                        case 0:
                            matrix.postTranslate(outW - inW, outH - inH);
                            matrix.postScale(scale, scale, outW, outH);
                            break;
                        case 90:
                            matrix.postRotate(rotate, 0, 0);
                            matrix.postTranslate(outW, outH - inH);
                            matrix.postScale(scale, scale, outW, outH);
                            break;
                        case 180:
                            matrix.postRotate(rotate, 0, 0);
                            matrix.postTranslate(outW, outH);
                            matrix.postScale(scale, scale, outW, outH);
                            break;
                        case 270:
                            matrix.postRotate(rotate, 0, 0);
                            matrix.postTranslate(outW - inW, outH);
                            matrix.postScale(scale, scale, outW, outH);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }

            try {
                outBmp = Bitmap.createBitmap(outW, outH, config);
                Canvas canvas = new Canvas(outBmp);
                canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
                canvas.drawBitmap(bmp, matrix, null);
            } catch (Exception e) {
                LogUtil.e(TAG, "", e);
            } catch (OutOfMemoryError e) {
                LogUtil.e(TAG, "", e);
                System.gc();
            }
        }

        return outBmp;
    }

    /**
     * 拉伸图片
     *
     * @param bmp    原始bitmap
     * @param outW   输出的图片宽
     * @param outH   输出的图片高
     * @param rotate 原始bitmap需要旋转的角度0,90,180,270...
     * @param config 像素类型
     */
    public static Bitmap CreateTensileBitmap(Bitmap bmp, int outW, int outH, int rotate, Bitmap.Config config) {
        Bitmap outBmp = null;
        if (bmp != null) {
            int inW = bmp.getWidth();
            int inH = bmp.getHeight();

            if (rotate % 180 != 0) {
                inW += inH;
                inH = inW - inH;
                inW -= inH;
            }

            Matrix matrix = new Matrix();
            matrix.postTranslate((outW - bmp.getWidth()) / 2f, (outH - bmp.getHeight()) / 2f);
            matrix.postRotate(rotate, outW / 2f, outH / 2f);
            matrix.postScale((float) outW / (float) inW, (float) outH / (float) inH, outW / 2f, outH / 2f);

            try {
                outBmp = Bitmap.createBitmap(outW, outH, config);
                Canvas canvas = new Canvas(outBmp);
                canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
                canvas.drawBitmap(bmp, matrix, null);
            } catch (Exception e) {
                LogUtil.e(TAG, "", e);
            } catch (OutOfMemoryError e) {
                LogUtil.e(TAG, "", e);
                System.gc();
            }
        }

        return outBmp;
    }

    /**
     * 图片绘制
     *
     * @param bgImage
     * @param drawImage
     * @return
     */
    public static Bitmap drawImage(Bitmap bgImage, Bitmap drawImage) {
        Bitmap bgTemp = bgImage.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bgTemp);
        canvas.drawBitmap(drawImage, 0, 0, null);
        canvas = null;
        return bgTemp;
    }


    public static int calculateInSampleSize2(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int h = options.outHeight;
        int w = options.outWidth;
        int inSampleSize = 0;
        if (h > reqHeight || w > reqWidth) {
            float ratioW = (float) w / reqWidth;
            float ratioH = (float) h / reqHeight;
            inSampleSize = (int) Math.min(ratioH, ratioW);
        }
        inSampleSize = Math.max(1, inSampleSize);
        return inSampleSize;
    }

    public static Bitmap getSmallBitmap(String filePath, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }


    // 获取图片的缩略图
    // 测试分析:
    // 原图大小为宽和高的像素值为:625x690,KB大小值为90.2kB.
    // 该图片在res中备份,作为测试图片
    // 调用方法computeSampleSize(options, 100, 100*200);
    // 表示希望得到的图片宽和高两者的最小值为100pixel,图片的总大小为100*200pixel.
    // 该方法返回的insampleSize=8,最终生成的图片宽和高分别为79和87
    // 在此做一个小验证:
    // 79*8=632 87*8=696 即为632x696,该值接近于原本的625x690
    public static Bitmap getBitmapThumbnail(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 将options.inJustDecodeBounds设置为true
        // 那么利用BitmapFactory.decodeXXX(...options...)将不返回实际的bitmap对象
        // 不给其分配内存空间但是可以得到图片的一些解码边界信息即图片大小等
        options.inJustDecodeBounds = true;
        // 此时rawBitmap为null
        Bitmap rawBitmap = BitmapFactory.decodeFile(filePath, options);
        if (rawBitmap == null) {
            System.out.println("---> 验证了上面的解释");
        }

        // 计算insampleSize
        // inSampleSize表示缩略图大小为原始图片大小的几分之一,若该值为3
        // 则取出的缩略图的宽和高的pixel均为原始图片的1/3,图片大小就为原始大小的1/9
        int insampleSize = computeSampleSize(options, 100, 100 * 200);
        options.inSampleSize = insampleSize;
        // 为BitmapFactory.decodeXXX(...options...)获取到实际的bitmap对象读到图片
        // 必须把options.inJustDecodeBounds设回false
        options.inJustDecodeBounds = false;
        Bitmap thumbnailBitmap = BitmapFactory.decodeFile(filePath, options);
        System.out.println("---> insampleSize=" + insampleSize + ",width=" +
                thumbnailBitmap.getWidth() + ",height=" + thumbnailBitmap.getHeight());
        return thumbnailBitmap;
    }

    //Android提供的一种动态计算的方法,得到恰当的inSampleSize
    //第一个参数:原本Bitmap的options
    //第二个参数:希望生成的缩略图的宽高中的pixel较小的值
    //第三个参数:希望生成的缩量图的总像素pixel
    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 :
                (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 :
                (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * 图片的缩放方法
     *
     * @param bgimage   ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    public static byte[] compressBitmapToBytes(String filePath, int reqWidth, int reqHeight, int quality, Bitmap.CompressFormat format) {
        Bitmap bitmap = getSmallBitmap(filePath, reqWidth, reqHeight);

        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(format, quality, baos);
        byte[] bytes = baos.toByteArray();
        bitmap.recycle();
        LogUtil.e(TAG, "Bitmap compressed success, size: " + bytes.length);
        return bytes;
    }

    public static byte[] compressBase64ToBytes(String base64, int quality, Bitmap.CompressFormat format) {
        Bitmap bitmap = Base64Util.base64ToBitmap(base64);

        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(format, quality, baos);
        byte[] bytes = baos.toByteArray();
        bitmap.recycle();
        LogUtil.e(TAG, "Bitmap compressed success, size: " + bytes.length);
        return bytes;
    }

    /**
     * view截图，webview和scrollview(scrollview需要传入子view)之类的view能够截取整个长度的bitmap，
     * 如果webview内容很多，view.draw(Canvas)方法会很耗时，在子进程中操作会有额外的问题，所以会暂时阻塞
     * UI主线程，求方法~
     */
    public static Bitmap viewShot(Context context, final View view) {
        if (view == null)
            return null;
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(measureSpec, measureSpec);

        if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            LogUtil.d("ImageUtils.viewShot size error");
            return null;
        }
        Bitmap bm;
        try {
            bm = Bitmap.createBitmap(DeviceUtil.getScreenWidth(context), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            System.gc();
            try {
                bm = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            } catch (OutOfMemoryError ee) {
                LogUtil.d("ImageUtils.viewShot error", ee.getMessage());
                return null;
            }
        }
        Canvas bigCanvas = new Canvas(bm);
        Paint paint = new Paint();
        int iHeight = bm.getHeight();
        bigCanvas.drawBitmap(bm, 0, iHeight, paint);
        view.draw(bigCanvas);
        return bm;
    }


    /**
     * 保存图片到手机相册，并通知图库更新
     *
     * @param context
     * @param bmp     图片bitmap
     * @return 返回图片保存的路径，开发人员可以根据返回的路径在手机里面查看，部分手机发送通知图库并不会更新
     */

    public static String saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "saveImage");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        String path = Environment.getExternalStorageDirectory() + "/saveImage/" + fileName;
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));

        return Environment.getExternalStorageDirectory() + "/saveImage/" + fileName;
    }


    /**
     * @param filePath
     * @param kind     kind could be MINI_KIND=1 or MICRO_KIND=3
     * @return
     */
    public static Bitmap createVideoThumbnail(String filePath, int kind) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            if (filePath.startsWith("http://")
                    || filePath.startsWith("https://")
                    || filePath.startsWith("widevine://")) {
                retriever.setDataSource(filePath, new Hashtable<String, String>());
            } else {
                retriever.setDataSource(filePath);
            }
            bitmap = retriever.getFrameAtTime(-1);
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
            ex.printStackTrace();
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
            ex.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
                ex.printStackTrace();
            }
        }

        if (bitmap == null) return null;

        if (kind == MediaStore.Images.Thumbnails.MINI_KIND) {
            // Scale down the bitmap if it's too large.
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int max = Math.max(width, height);
            if (max > 512) {
                float scale = 512f / max;
                int w = Math.round(scale * width);
                int h = Math.round(scale * height);
                bitmap = Bitmap.createScaledBitmap(bitmap, w, h, true);
            }
        } else if (kind == MediaStore.Images.Thumbnails.MICRO_KIND) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap,
                    96,
                    96,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }


}
