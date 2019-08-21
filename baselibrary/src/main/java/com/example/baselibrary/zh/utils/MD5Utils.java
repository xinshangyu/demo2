package com.example.baselibrary.zh.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class MD5Utils {

    private static final int BUFFERSIZE = 8196;
    private static final String ALGORITHM = "MD5";
    private static final String CHARSET = "UTF-8";

    public MD5Utils() {
    }

    public static String md5(String input) {
        return md5(input, CHARSET);
    }

    private static String md5(String input, String charsetName) {
        try {
            MessageDigest md5 = MessageDigest.getInstance(ALGORITHM);
            byte md5Bytes[] = md5.digest(input.getBytes(charsetName));
            return StringUtils.byte2hex(md5Bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return input;
    }

    public static String md5file(File filename) {
        BufferedInputStream bufferedInputStream = null;
        MessageDigest md;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(filename), BUFFERSIZE);
            md = MessageDigest.getInstance(ALGORITHM);
            byte[] buffer = new byte[BUFFERSIZE];
            int i = 0;
            while ((i = bufferedInputStream.read(buffer)) != -1) {
                md.update(buffer, 0, i);
            }
            return StringUtils.byte2hex(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                bufferedInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String md5reverse(String md5) {
        return "";
    }

}
