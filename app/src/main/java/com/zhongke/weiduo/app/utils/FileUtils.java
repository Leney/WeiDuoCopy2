package com.zhongke.weiduo.app.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;

import com.zhongke.weiduo.app.ZkApplication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karma on 2017/6/08.
 * 类描述：文件类工具类
 */
public class FileUtils {
    public static final String ROOT_DIR = "Android/data/"
            + UIUtils.getPackageName();
    private final static String[][] MIME_MapTable = {
            //{后缀名，MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };

    static Context mApplicationContext = ZkApplication.getInstance();

    public static File getDiskCacheDir(String uniqueName) {

        String cachePath;
        if (!"mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) {

            if (mApplicationContext.getCacheDir() == null) {
                File dcim = Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                cachePath = dcim + "/response";
            } else {
                cachePath = mApplicationContext.getCacheDir().getPath();
            }


        } else {

            if (mApplicationContext.getExternalCacheDir() == null) {
                File dcim = Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                cachePath = dcim + "/response";
            } else {
                cachePath = mApplicationContext.getExternalCacheDir().getPath();
            }


        }

        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 获取文件名
     *
     * @param filepath dir+filename
     */
    public static String getFileNameFromPath(String filepath) {
        if ((filepath != null) && (filepath.length() > 0)) {
            int sep = filepath.lastIndexOf('/');
            if ((sep > -1) && (sep < filepath.length() - 1)) {
                return filepath.substring(sep + 1);
            }
        }
        return filepath;
    }

    /**
     * 判断SD卡是否挂载
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检测文件是否可用
     */
    public static boolean checkFile(File f) {
        if (f != null && f.exists() && f.canRead() && (f.isDirectory() || (f.isFile() && f.length() > 0))) {
            return true;
        }
        return false;
    }

    /**
     * SD卡是否存在
     */
    public static boolean isSDexist() {
        //SD卡是否存在
        boolean isExist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        return isExist;
    }

    /**
     * 获取SD卡路径
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     */
    public static long getSDCardAllSize() {
        if (isSDexist()) {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取系统存储路径
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 创建文件夹
     *
     * @param folderName 目录名字，如果创建多个目录，传入的参数请这样写 "XXX/XXX/XXx/XXX"
     *                   默认情况下，有SD卡的时候，默认目录为SD卡下，已经初始化好，因此传参无需写入SD啊目录 允许在文件夹存在时创建
     */
    public static boolean createFolder(String rootPath, String folderName) {
        boolean successToCreate = false;
        String ROOT_PATH = "";
        // 判断SD卡
        boolean isSdCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        // 默认根路径
        if (!rootPath.equals("")) {
            if (String.valueOf(rootPath.length()).equals("/")) {
                ROOT_PATH = rootPath;
            } else {
                ROOT_PATH = rootPath + "/";
            }
        } else {
            if (isSdCard) {
                ROOT_PATH = Environment.getExternalStorageDirectory() + File.separator;
            } else {
                ROOT_PATH = "/data/data/" + mApplicationContext.getPackageName() + "/";
            }
        }
        // 将String路径根据“/”抽取
        String[] PATHS = folderName.split("/");
        List<String> pathsList = new ArrayList<>();
        // 将path处理后加到list里面
        for (int i = 0; i < PATHS.length; i++) {
            if (i == 0 && !PATHS[0].equals("")) {
                pathsList.add(0, ROOT_PATH + PATHS[0]);
            } else if (i > 0 && !PATHS[i].equals("")) {
                pathsList.add(i, pathsList.get(i - 1) + "/" + PATHS[i]);
            }
        }
        for (int i = 0; i < pathsList.size(); i++) {
            if (i == 0) {
                File firstFolder = new File(pathsList.get(0));
                if (!firstFolder.exists()) {
                    successToCreate = firstFolder.mkdir();
                }
            } else {
                File preFolder = new File(pathsList.get(i - 1));
                File curFolder = new File(pathsList.get(i));
                if (preFolder.exists()) {
                    successToCreate = curFolder.mkdir();
                } else {
                    successToCreate = preFolder.mkdir();
                    successToCreate = curFolder.mkdir();
                }
            }
        }
        return successToCreate;
    }

    /**
     * @param fromFile 源文件路径，如"/sdcard/xxx/xxx.txt"
     * @param toFile   目标文件路径，如"xxx/xxx/xxx.txt"
     * @param rewrite  可否重写，如果可以，则会覆盖文件.
     * @return String信息
     * @throws IOException 异常处理
     */
    public static String copyfile(String fromFile, String toFile, Boolean rewrite) throws IOException {
        File from = new File(fromFile);
        File to = new File(toFile);
        if (!from.isFile()) {
            return "错误，请注意填写路径";
        }
        if (!from.exists()) {
            return "错误，文件不存在，请注意填写路径";
        }
        if (!from.canRead()) {
            return "错误，文件不可读，请注意权限";
        }
        // 判断目标路径的父文件夹是否存在，不存在就建一个
        if (!to.getParentFile().exists()) {
            to.getParentFile().mkdir();
        }
        // 判断目标文件是否存在以及是否可以复写，如果都满足，则删除原来的目标文件，否则，则在原文件后面加上-new
        if (to.exists() && rewrite) {
            to.delete();
        } else if (to.exists() && !rewrite) {
            String newToFile = getFileNameNoEx(toFile) + "-new." + getExtensionName(toFile);
            File newFile = new File(newToFile);
            to = newFile;
        }

        BufferedInputStream fromInPut = null;
        BufferedOutputStream toOutPut = null;
        // 以下为为复制
        try {
            fromInPut = new BufferedInputStream(new FileInputStream(from));
            toOutPut = new BufferedOutputStream(new FileOutputStream(to));
            int reader = 0;
            int bytesCopied = 0;
            while ((reader = fromInPut.read()) != -1) {
                toOutPut.write((byte) reader);
                bytesCopied++;
            }
            return "成功复制文件，一共复制了:" + bytesCopied + "bytes" + '\n' + "源路径：" + from.getAbsolutePath()
                    + '\n' + "目标路径：" + to.getAbsolutePath();
        } finally {
            fromInPut.close();
            toOutPut.close();
        }
    }

    // 获取后缀名
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    // 获取无扩展名的文件名
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 获取指定文件夹指定的后缀的文件路径，并添加前缀且存放到list中（建议在子线程中完成）
     *
     * @param folderPath 文件夹的路径
     * @param prefixion  前缀，如file://
     * @param type       类型，请输入后缀名，如jpg,png甚至txt什么的
     * @return List<Sring>
     */
    public static List<String> getFilesFromFolder(String folderPath, String prefixion,
                                                  String type) {
        String extensionName = getExtensionName(type);// 统一后缀格式（去掉.）
        List<String> fileList = new ArrayList<String>();
        File[] files;
        String path = "";
        // 判断文件夹是否存在
        if ((new File(folderPath).isDirectory())) {
            files = new File(folderPath).listFiles();
            for (File f : files) {
                path = f.getAbsolutePath();
                if (!extensionName.equals("*")) {
                    if (!f.isDirectory() && path.endsWith(extensionName)) {
                        fileList.add(prefixion + path);
                    }
                } else {
                    if (extensionName.equals("*")) {
                        if (!f.isDirectory()) {
                            fileList.add(prefixion + path);
                        }
                    }
                }
            }
        } else {
            return null;
        }
        return fileList;
    }

    // 保存字节到文件
    public static void saveBytesToSD(String filePath, String fileName, byte[] data) {
        if (data != null) {
            FileOutputStream fos = null;
            try {
                File file = new File(filePath, fileName);
                fos = new FileOutputStream(file);
                fos.write(data);
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {
                    if (fos != null) {
                        fos.close();
                        fos = null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 从SD得到文字内容文件
    public static byte[] getBytesFromSD(String filePath) {
        FileInputStream fis = null;
        byte[] buf = null;
        try {
            File file = new File(filePath);
            if (file.exists() == true) {
                fis = new FileInputStream(file);
                buf = new byte[(int) file.length()];
                fis.read(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (Exception e) {
            }
        }
        return buf;
    }

    //将文件夹中的文件按修改时间进行排序
    public static void Filecompositor(File[] files) {
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                for (int j = i + 1; j < files.length; j++) {
                    if (files[i].lastModified() < files[j].lastModified()) {
                        File f = files[j];
                        files[j] = files[i];
                        files[i] = f;
                    }
                }
            }
        }
    }


    //------------------------------------------根据文件名删除文件-----------------------------------------------
    public static boolean delete(String filename, String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].toString().equals(path + "/" + filename)) {
                return files[i].delete();
            }
        }
        return false;
    }


    /**
     * 打开文件
     *
     * @param file
     */
    public static void openFile(Context context, File file) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        //获取文件file的MIME类型
        String type = getMIMEType(file);
        //设置intent的data和Type属性。
        intent.setDataAndType(Uri.fromFile(file), type);
        //跳转
        try {
            context.startActivity(intent);     //这里最好try一下，有可能会报错。 //比如说你的MIME类型是打开邮箱，但是你手机里面没装邮箱客户端，就会报错。
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }

    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file
     */
    private static String getMIMEType(File file) {

        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
    /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "") return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) { //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    /**
     * 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录
     */
    public static String getDir(String name) {
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getExternalStoragePath());
        } else {
            sb.append(getCachePath());
        }
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return null;
        }
    }

    /**
     * 创建文件夹
     */
    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 获取SD下的应用目录
     */
    public static String getExternalStoragePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(ROOT_DIR);
        sb.append(File.separator);
        return sb.toString();
    }

    /**
     * 获取应用的cache目录
     */
    public static String getCachePath() {
        File f = UIUtils.getContext().getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }


}
