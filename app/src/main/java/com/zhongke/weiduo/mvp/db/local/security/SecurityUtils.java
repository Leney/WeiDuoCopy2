package com.zhongke.weiduo.mvp.db.local.security;

import android.util.Base64;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by ${xingen} on 2017/7/10.
 *
 * 加密类，这里采用AES对称加密，将本地数据进行中数据加密。
 */

public class SecurityUtils {

    //16长度的数组
    private static final byte[] keyValues = {107, -76, -125, 126, -73, 67, 41, 16, 94, -28, 86, -115, -38, 125, -58, 126};
    private static final int KEY_SIZE = 128;

    /**
     * @return
     */
    private static SecretKey getKey() {
        try {
            //密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //android7.0以上"Crypto"被移除了，7.0一下可以用
            // 出现 javax.crypto.BadPaddingException: pad block corrupted
            SecureRandom secureRandom=null;
            if(android.os.Build.VERSION.SDK_INT <=23&&android.os.Build.VERSION.SDK_INT >= 17){//android 4.2 即Api17
                secureRandom = SecureRandom.getInstance("SHA1PRNG","Crypto");
            }else{
                secureRandom=  SecureRandom.getInstance("SHA1PRNG");
            }
            secureRandom.setSeed(keyValues);
            keyGenerator.init(KEY_SIZE, secureRandom);
            //密钥
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static SecretKeySpec fromSecretKey(SecretKey secretKey) {
        try {
            return new SecretKeySpec(secretKey.getEncoded(), "AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 编码String
     *
     * @param origin
     * @return
     */
    private static byte[] codingStr(String origin) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, fromSecretKey(getKey()));
            return cipher.doFinal(origin.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解码String
     *
     * @param encode
     * @return
     */
    private static byte[] decodeStr(byte[] encode) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, fromSecretKey(getKey()));
            return cipher.doFinal(encode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Base64编码
     *
     * @param originData
     * @return
     */
    public static String encryptData(String originData) {
        //android 7.0的AES加密
        if(android.os.Build.VERSION.SDK_INT >=24){
            int keySizeInBytes=KEY_SIZE;
            //InsecureSHA1PRNGKeyDerivator帮助类
            SecretKey insecureKey=new SecretKeySpec(  InsecureSHA1PRNGKeyDerivator.deriveInsecureKey(keyValues, keySizeInBytes),"AES");
            try{
                SecretKeySpec skeySpec = new SecretKeySpec(insecureKey.getEncoded(), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING");
                cipher.init(Cipher.ENCRYPT_MODE,skeySpec,new IvParameterSpec(keyValues));
                byte[] encrypted= cipher.doFinal(originData.getBytes("utf-8"));
                return Base64.encodeToString(encrypted, Base64.DEFAULT);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }else{
            return Base64.encodeToString(codingStr(originData), Base64.DEFAULT);
        }
    }



    /**
     * Base64解码
     *
     * @param encryptData
     * @return
     */
    public static String decodeData(String encryptData) {
        if(android.os.Build.VERSION.SDK_INT >=24){
            int keySizeInBytes=KEY_SIZE;
            //InsecureSHA1PRNGKeyDerivator帮助类
            SecretKey insecureKey=new SecretKeySpec(  InsecureSHA1PRNGKeyDerivator.deriveInsecureKey(keyValues, keySizeInBytes),"AES");
            try{
                SecretKeySpec skeySpec = new SecretKeySpec(insecureKey.getEncoded(), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING");
                cipher.init(Cipher.DECRYPT_MODE,skeySpec,new IvParameterSpec(keyValues));
                byte[] encrypt= Base64.decode(encryptData, Base64.DEFAULT);
                return new String( cipher.doFinal(encrypt));
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }else{
            try {
                return new String(decodeStr(Base64.decode(encryptData, Base64.DEFAULT)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
