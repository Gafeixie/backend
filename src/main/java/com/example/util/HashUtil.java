package com.example.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Sihon
 * @version 1.0
 * @since 2021/5/10 0:26
 */
public class HashUtil {

    public static String getHash(String file) throws NoSuchAlgorithmException, IOException {
        FileInputStream fis = new FileInputStream(file);
        return getHash(fis);

    }

    public static String getHash(FileInputStream fis) throws NoSuchAlgorithmException, IOException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");


        byte[] buffer = new byte[1024];
        int length = -1;
        while ((length = fis.read(buffer, 0, 1024)) != -1) {
            md.update(buffer, 0, length);
        }
        fis.close();

        byte[] digest = md.digest();
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            int val = ((int) digest[i]) & 0xff;//解释参见最下方
            if (val < 16) {
                /*
                 * 如果小于16，那么val值的16进制形式必然为一位，
                 * 因为十进制0,1...9,10,11,12,13,14,15 对应的 16进制为 0,1...9,a,b,c,d,e,f;
                 * 此处高位补0。
                 */
                hexValue.append("0");
            }
            //这里借助了Integer类的方法实现16进制的转换
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
