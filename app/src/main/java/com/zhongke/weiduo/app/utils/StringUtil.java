package com.zhongke.weiduo.app.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by  karma on 2017/06/19.
 * 类描述：字符串判断工具类
 */
public class StringUtil {

    private static final String UTF_8 = "UTF-8";

    private static List<String> citySuffixList = new ArrayList<String>();

   /* static {
        citySuffixList.add("省");
        citySuffixList.add("特别行政区");
        citySuffixList.add("直辖市");
        citySuffixList.add("市");
        citySuffixList.add("自治区");
        citySuffixList.add("自治州");
        citySuffixList.add("区");
        citySuffixList.add("林区");
        citySuffixList.add("县");
        citySuffixList.add("自治县");
    }*/


    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getNotNullStr(String val) {
        return val == null ? "" : val;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return str == null || "".equals(str.trim()) || "null".equals(str.trim());
    }

    /**
     * 判断参数序列是否有空
     *
     * @param params
     * @return boolean
     */
    public static boolean isNull(String... params) {
        boolean flag = false;
        for (String str : params) {
            if (null == str || "".equals(str)) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    public static String getDataSize(long var0) {
        DecimalFormat var2 = new DecimalFormat("###.00");
        return var0 < 1024L ? var0 + "bytes" : (var0 < 1048576L ? var2.format((double) ((float) var0 / 1024.0F)) + "KB" : (var0 < 1073741824L ? var2.format((double) ((float) var0 / 1024.0F / 1024.0F)) + "MB" : (var0 < 0L ? var2.format((double) ((float) var0 / 1024.0F / 1024.0F / 1024.0F)) + "GB" : "error")));
    }

    /**
     * 生成一个指定范围的随机数
     */
    public static int getRandomDigit(int minVal, int maxVal) {
        Random random = new Random();
        return random.nextInt(maxVal - minVal + 1) + minVal;
    }

    /**
     * URL解码（UTF-8）
     *
     * @param value
     * @return String
     * @throws Exception
     */
    public static String decode4UTF8(String value) throws Exception {
        return URLDecoder.decode(value, UTF_8);
    }

    /**
     * URL编码（UTF-8）
     *
     * @param param
     * @return String
     * @throws Exception
     */
    public static String encoder4UTF8(String param) throws Exception {
        return URLEncoder.encode(param, UTF_8);
    }

    /**
     * 将动态数组转换成List
     *
     * @param params
     * @return List<String>
     */
    public static List<String> dynaStrToList(String... params) {
        List<String> list = new ArrayList<String>();
        if (params == null || params.length == 0) {
            return list;
        }
        for (String str : params) {
            list.add(str);
        }
        return list;
    }

    /**
     * @param length
     * @return String
     * @description 生成指定位数的随机数
     */
    public static String getRandomDigit(int length) {
        StringBuffer rs = new StringBuffer();
        int i = 0;
        Random random = new Random();
        while (i < length) {
            rs.append(random.nextInt(9));
            i++;
        }
        return rs.toString();
    }

    /**
     * @param --length
     * @return String
     * @description 生成指定位数的字符串
     */
    public static String getRandomString(int n) {
        String[] s = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String rs = "";
        int i = 0;
        Random r = new Random();
        while (i < n) {
            int j = r.nextInt(s.length);
            rs += s[j];
            i++;
        }
        return rs;
    }

    /**
     * 将List拼装成String
     *
     * @param list
     * @param split 拼装时的分隔符号
     * @return String
     */
    public static String getString(List<?> list, String split) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Object obj : list) {
            sb.append(obj.toString() + split);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    /**
     * 删除城市的后缀
     *
     * @param cityName
     * @return String
     */
    public static String removeCitySuffix(String cityName) {
        if (isNull(cityName)) {
            return cityName;
        }
        int len = cityName.length();
        int index = -1;
        int size = 0;
        for (String key : citySuffixList) {
            index = cityName.lastIndexOf(key);
            if (index > 0) {
                size = key.length();
                break;
            }
        }
        if (index == len - size) {
            return cityName.substring(0, index);
        }
        return cityName;
    }

    public static String toMD5(String source) {
        if (null == source || "".equals(source))
            return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(source.getBytes());
            return toHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

    private final static String HEX = "0123456789ABCDEF";

    public static int getChatNo() {
// return (int) System.currentTimeMillis();
        long t = System.currentTimeMillis(); // 从2015年1月1日起
        int id = (int) ((t - 1420041600000L) / 100);
        return id;
    }

    /**
     * 判断两个字符串是否相等<br>
     * NULL 和 ""不等
     */
    public static boolean isEquals(String str1, String str2) {
        if (isNull(str1) && isNull(str2)) {
            return true;
        }
        if (!isNull(str1) && str1.equals(str2)) {
            return true;
        }
        if (!isNull(str2) && str2.equals(str1)) {
            return true;
        }
        return false;
    }

    /**
     * 判断两个字符串是否相等<br>
     * NULL 和 "" 相等，且是去格进行比较
     */
    public static boolean isEqualsIgnoreNull(String str1, String str2) {
        if (isNull(str1)) {
            str1 = "";
        }
        if (isNull(str2)) {
            str2 = "";
        }
        str1.trim();
        str2.trim();
        return str1.equals(str2);
    }


    /**
     * 获取字节长度
     */
    public static int getLength4Byte(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255)
                length++;
            else
                length += 2;

        }
        return length;
    }

    /**
     * 根据字节长度截取字符串
     */
    public static String subString4Byte(String s, int len) {
        if (isNull(s)) {
            return "";
        }

        int length = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
            if (length <= len) {
                sb.append(s.substring(i, i + 1));
            } else {
                break;
            }
        }

        return sb.toString();
    }

    /**
     * 根据后缀添加字符
     *
     * @param sourceStr 源字符串
     * @param validStr  从字符串最后面验证的字符串
     * @param appStr    要添加的字符串，如果验证的字符串是在最末，则不添加，否则添加
     * @return String
     */
    public static String appendLast(String sourceStr, String validStr, String appStr) {
        if (StringUtil.isNull(sourceStr)) {
            return "";
        }

        if (sourceStr.lastIndexOf(validStr) != sourceStr.length() - validStr.length()) {
            sourceStr += appStr;
        }
        return sourceStr;
    }

    /**
     * 字符串填充，当实际
     *
     * @param src
     * @param length  字节长度
     * @param mark    要直填充的字符
     * @param isAfter 是否从后面填充
     * @return String
     */
    public static String fill(String src, int length, String mark, boolean isAfter) {
        if (isNull(src)) {
            src = "";
        }

        int len = 0;
        for (int i = 0; i < src.length(); i++) {
            int ascii = Character.codePointAt(src, i);
            if (ascii >= 0 && ascii <= 255) {
                len++;
            } else {
                len += 2;
            }
        }

        if (length <= len) {
            return src;
        }
        for (int i = 0; i < length - len; i++) {
            if (isAfter) {
                src += mark;
            } else {
                src = mark + src;
            }
        }

        return src;
    }

// ============================================================

    public static String getStringVal(Object obj, String defaultVal) {
        if (obj != null) {
            return obj.toString();
        }
        return defaultVal;
    }

    public static Integer getIntegerVal(Object val, Integer defaultVal) {
        if (val == null) {
            return defaultVal;
        }
        return Integer.valueOf(val.toString());
    }

    public static Long getLongVal(Object val, Long defaultVal) {
        if (val == null) {
            return defaultVal;
        }
        return Long.valueOf(val.toString());
    }

    public static Double getDoubleVal(Object val, Double defaultVal) {
        if (val == null) {
            return defaultVal;
        }
        return Double.valueOf(val.toString());
    }

// ============================================================

    public static void openKeyboard(Context mContext) {

        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(mContext.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }

    // 关闭软键盘
    private void closeKeyboard(Context mContext) {
// InputMethodManager imm = (InputMethodManager) mContext.getSystemService(mContext.INPUT_METHOD_SERVICE);
// imm.hideSoftInputFromWindow(et_searchword_word.getWindowToken(), 0);
    }

    /**
     * 去除“第”之前的所有非汉字内容
     */
    public static String clearNotChinese(String buff) {
        String tmpString = buff.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");// 去掉所有中英文符号
        char[] carr = tmpString.toCharArray();
        for (int i = 0; i < tmpString.length(); i++) {
            if (carr[i] < 0xFF) {
                carr[i] = ' ';// 过滤掉非汉字内容
            }
        }
        return String.copyValueOf(carr).trim();
    }

    /**
     * 清除所有的中英文的标点符号
     *
     * @param str
     * @return
     */
    public static String clearPunctuation(String str) {
        if (!isNull(str))
            return str.replaceAll("[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", "");
        return null;
    }

    public static final char UNDERLINE = '_';

    /**
     * mingliang.mai
     *
     * @param param
     * @return 驼峰转下划线
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    /**
     * mingliang.mai
     *
     * @param param
     * @return 下划线驼峰
     */
    public static String underline2Camel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * mingliang.mai 下划线驼峰
     *
     * @param param
     * @return
     */
    public static String underline2Camel2(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    public static String encodeURL(String string) {
        try {
            return URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decodeURL(String string) {
        try {
            return URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String friendShowNum(int num) {

        if (num < 1000) {
            return num + "";
        } else if (num >= 1000 && num < 10000) {
            return formatDouble2(num / 1000.0) + "k";
        } else if (num >= 10000) {
            return formatDouble2(num / 10000.0) + "w";
        }

        return "0";
    }

    /**
     * DecimalFormat is a concrete subclass of NumberFormat that formats decimal numbers.
     *
     * @param d
     * @return
     */
    public static String formatDouble2(double d) {
        DecimalFormat df = new DecimalFormat("#0.0");
        return df.format(d);
    }

    /**
     * DecimalFormat is a concrete subclass of NumberFormat that formats decimal numbers.
     *
     * @param d
     * @return
     */
    public static String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(d);
    }

    /**
     * 根据uri获取真实url路径
     * @param context
     * @param uri
     * @return
     */
    public static String getRealFilePath( final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 真实路径转uri
     * @param imageName
     * @return
     */
    public static Uri getImageStreamFromExternal(String imageName) {
        File externalPubPath = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
        );

        File picPath = new File(externalPubPath, imageName);
        Uri uri = null;
        if(picPath.exists()) {
            uri = Uri.fromFile(picPath);
        }

        return uri;
    }

}
