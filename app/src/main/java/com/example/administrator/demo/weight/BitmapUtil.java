package com.example.administrator.demo.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


/**
 * FileName: 图片相关工具
 * Author :  杜晓武
 * Create : 2018/10/10 14:44
 */
public class BitmapUtil {
    private static final String TAG = "BitmapUtil";
    private static final String TIME_STYLE = "yyyyMMdd_HHmmss";

    private static final String ROOT_PATH = "/xsy/";

    private static final String PIC_SUFFIX = ".jpg";
    public BitmapUtil(){}

    Context context;
    public BitmapUtil(Context context){
        this.context = context;
    }



    public static String getPicDefaultPath(Context context,String path){
        String savePath;
        File filePic;
        String fileName = getDefautlPicName();
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            savePath =  Environment
                    .getExternalStorageDirectory()
                    + ROOT_PATH + path;
        } else {
            savePath = context.getApplicationContext().getFilesDir()
                    .getAbsolutePath();
        }
        try {
            filePic = new File(savePath,fileName);
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filePic.getAbsolutePath();
    }


    public static String getDefautlPicName(){
        // 设置图片文件名称
        SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE, Locale.getDefault());
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        String photoName = get8UUID()+"_"+time+PIC_SUFFIX;
        return photoName;
    }
    public static String get8UUID(){
        String ranStr = UUID.randomUUID().toString().replace("_", "").substring(0, 8);
        return ranStr;
    }
    public static String getPhotoNameWithType(String source) {
        // 设置图片文件名称
        SimpleDateFormat format = new SimpleDateFormat(TIME_STYLE, Locale.getDefault());
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        String suffix = TextUtils.isEmpty(getFileSuffix(source))?".jpg":getFileSuffix(source);
        String photoName = get8UUID()+"_"+time+suffix;
        return photoName;
    }
    public static long getFileSize(String filePath){
        long folderSize = 0;
        try {
            folderSize = FileTool.getFolderSize(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return folderSize;
    }
    private static String getFileSuffix(String path){
        if(TextUtils.isEmpty(path)||path.length() < 2)return "";
        return path.substring(path.lastIndexOf("."),path.length());
    }


    //返回图片压缩 后的新路径 - 头像专用
    public static String compressToNewPath(Context context,int imageSize,String sourcePath) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(sourcePath, options);
        //outMimeType是以--”image/png”、”image/jpeg”、”image/gif”…….这样的方式返回的
        String mimeType = options.outMimeType;
        if("image/gif".equals(mimeType)){//gif不压缩，先保证能动
            return sourcePath;
        }

        String path;
        String pathNew = CompressPhoto.getDefaultPicPath(context,sourcePath);
        boolean b = CompressPhoto.compressBitmap(sourcePath, imageSize, pathNew);
        //imageSize 为目标 xxk以下，。。
//        if(b){
//            path = pathNew;
//        }else {
            path = sourcePath;
//        }

        return path;
    }
}
