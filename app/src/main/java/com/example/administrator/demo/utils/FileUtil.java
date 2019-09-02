package com.example.administrator.demo.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/**
 * Created by lidongheng on 2019/8/15.
 */

public class FileUtil {

    public static final String ROOT_PATH_NAME = "xinshangyu";

    public static final String ROOT_IMAGE_PATH = "IMG";

    public static String getRootPath() {
        return Environment.getExternalStorageDirectory().toString() + "/" + ROOT_PATH_NAME;
    }

    /**
     * 获取图片路径
     *
     * @param caseNo 案件号
     * @return
     */
    public static String getImgPath(String caseNo) {
        String imagePath = getRootPath() + "/" + ROOT_IMAGE_PATH + "/" + caseNo + "/";
        File file = null;
        if (!(file = new File(imagePath)).exists()) {
            file.mkdirs();
        }
        return imagePath;
    }

    /**
     * 获取日志路径
     *
     * @return
     */
    public static String getLogPath() {
        String logPath = getRootPath() + "/logs/";
        File file = null;
        if (!(file = new File(logPath)).exists()) {
            file.mkdirs();
        }
        return logPath;
    }

    /**
     * 将byte[]保存成文件
     *
     * @return
     */
    public static boolean saveStreamToFile(byte[] bt, String path) {
        boolean bl = false;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(bt, 0, bt.length);
            fileOutputStream.flush();
            bl = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bl;
    }

    /*** 复制单个文件
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    public static void copyFileUsingFileChannels(String source, String dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }
}
