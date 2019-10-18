package com.example.administrator.demo.weight;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import okhttp3.Request;
import okhttp3.Response;

/**
 * FileName:
 * Author :  杜晓武
 * Create : 2018/10/10 15:40
 */
public class FileTool {

    private static final String TAG = FileTool.class.getSimpleName();
    private static final String ROOT_PATH = "/xsy/";

    /**
     * 文件夹大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            if (file.isDirectory()) {
                File[] fileList = file.listFiles();
                for (int i = 0; i < fileList.length; i++) {
// 如果下面还有文
                    if (fileList[i].isDirectory()) {
                        size = size + getFolderSize(fileList[i]);
                    } else {
                        size = size + fileList[i].length();
                    }
                }
            } else if (file.isFile()) {
                size = file.length();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;

        if (kiloByte < 1) {

            return size + "B";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {

            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));

            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";

        }

        double gigaByte = megaByte / 1024;

        if (gigaByte < 1) {

            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));

            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;

        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));

            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }



    //下载到指定文件

    //子线程里操作
    public static File returnFile(Context context, final String url, String path) {
        URL imageurl = null;
        if (TextUtils.isEmpty(url)) return null;
        String dir = saveFileDefault(context, url, path);
        if (TextUtils.isEmpty(dir)) return null;
        File file = new File(dir);
        try {
            imageurl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        FileOutputStream outputStream = null;
        InputStream is = null;
        try {
            if (imageurl == null) return null;
            HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.connect();
            is = conn.getInputStream();
            outputStream = new FileOutputStream(file);
            if (conn.getResponseCode() == 200) {
                byte[] buffer = new byte[4096];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
            } else {
//                ToastUtil.showToast(context.getResources().getString(R.string.im_fu_wu_qi));
            }
            outputStream.flush();
            is.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * @param url  下载连接
     * @param path 下载的文件储存目录
     *             //     * @param listener     下载监听
     */

    private static final String AUDIO_PATH = "audio/";
    private static final String IMAGE_PATH = "image/";
    private static final String VIDEO_PATH = "video/";
    private static final String PRIVATE_IMAGE_PATH = "emoji/";

    private static String saveFileDefault(Context context, String source, String path) {
        String savePath;
        File filePic;
        String fileName = getPhotoNameWithType(source);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath = Environment
                    .getExternalStorageDirectory()
                    + ROOT_PATH + path;
        } else {
            savePath = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath();
        }
        try {
            filePic = new File(savePath, fileName);
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filePic.getAbsolutePath();
    }
    public static String get8UUID(){
        String ranStr = UUID.randomUUID().toString().replace("_", "").substring(0, 8);
        return ranStr;
    }
    private static final String TIME_STYLE = "yyyyMMdd_HHmmss";

    public static String getPhotoNameWithType(String source) {
        // 设置图片文件名称
        SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE, Locale.getDefault());
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        String photoName = get8UUID() + "_" + time + getFileSuffix(source);
        return photoName;
    }

    private static String getFileSuffix(String path) {
        if (TextUtils.isEmpty(path) || path.length() < 2) return "";
        if (path.lastIndexOf(".") < 0) return "";
        return path.substring(path.lastIndexOf("."), path.length());
    }
}
