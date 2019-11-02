package com.example.administrator.demo.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/6/15.
 */

public class CompressPhoto {

    private static final String TAG = "CompressPhoto";
    static String path;
    static String picPath;
    private static final String SD_ROOT = "/xsy/";

    public static String getPicNewPath(String sourcePath, String picName){

        BitmapFactory.Options o = new BitmapFactory.Options();
        /** 只取宽高防止oom */
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(sourcePath, o);
        BitmapFactory.Options options=new BitmapFactory.Options();

        options.inSampleSize=2;
        /** 内存不足的时候可被擦除 */
        options.inPurgeable = true;
        /** 深拷贝 */
        options.inInputShareable = true;

        Bitmap result = null;
        synchronized (CompressPhoto.class) {
            result = BitmapFactory.decodeFile(sourcePath, options);
        }// 拿到图片
        FileOutputStream fileOutputStream = null;
        try {
            String saveDir = Environment
                    .getExternalStorageDirectory()
                    + SD_ROOT;
// 新建目录
             File dir = new File(saveDir);
        if (!dir.exists())
            dir.mkdir();

            String filename = "";
            if(picName.endsWith(".jpg")||picName.endsWith(".png")){
                filename = picName;
            }else {
                filename = picName+".jpg";
            }
// 新建文件
        File file = new File(saveDir, filename);
// 打开文件输出流

            fileOutputStream = new FileOutputStream(file);

            // 生成图片文件
            result.compress(Bitmap.CompressFormat.JPEG,
                    100, fileOutputStream);

// 相片的完整路径          //尺寸压缩
            picPath = file.getPath();

            //画质压缩
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inPreferredConfig = Bitmap.Config.RGB_565;

            if(result != null){
                if(!result.isRecycled()){
                    result.recycle();
                }
            }
//        imageView.setImageBitmap(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return picPath;
    }

    public static Bitmap compressPicToBitmap(String sourcePath,String picName){

        Bitmap photo = BitmapFactory.decodeFile(sourcePath); // 拿到图片

        FileOutputStream fileOutputStream = null;
        try {
            String saveDir = Environment
                    .getExternalStorageDirectory()
                    + SD_ROOT;
// 新建目录
            File dir = new File(saveDir);
            if (!dir.exists())
                dir.mkdir();
// 生成文件名
            String filename = picName+".jpg";
// 新建文件
            File file = new File(saveDir, filename);
// 打开文件输出流

            fileOutputStream = new FileOutputStream(file);
            // 生成图片文件
            photo.compress(Bitmap.CompressFormat.JPEG,
                    100, fileOutputStream);

// 相片的完整路径          //尺寸压缩
            picPath = file.getPath();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            photo = BitmapFactory.decodeFile(picPath, options);

            //画质压缩
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inPreferredConfig = Bitmap.Config.RGB_565;
//            photo = BitmapFactory.decodeFile(picPath, options2);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return photo;
    }

        /**
         * 根据分辨率压缩
         ** @param srcPath 图片路径
         * @param ImageSize 图片大小 单位kb
         * @return
         */
    public static boolean compressBitmap(String srcPath, int ImageSize, String savePath) {
        int subtract;
        if(BitmapUtil.getFileSize(srcPath) < ImageSize *1024){
            return false;
        }
        Bitmap orgBitmap = compressByResolution(srcPath, 1080, 1920); //分辨率压缩
        if (orgBitmap == null) {
            return false;
        }
        //图片角度旋转
        int angle = PhotoBitmapUtils.readPictureDegree(srcPath);
        Bitmap bitmap;
        if(angle>0){//判断是否有旋转
              bitmap = PhotoBitmapUtils.rotaingImageView(angle, orgBitmap);
        }else {
           bitmap=orgBitmap;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        try {
            while (baos.toByteArray().length > ImageSize * 1024) { //循环判断如果压缩后图片是否大于ImageSize kb,大于继续压缩
                subtract = setSubstractSize(baos.toByteArray().length / 1024);
                baos.reset();//重置baos即清空baos
                options -= subtract;//每次都减少10
                if(options < 5){
                    options = 5;
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            }
//        LogU.i(TAG, "图片处理完成!" + baos.toByteArray().length / 1024 + "KB");

            FileOutputStream fos = new FileOutputStream(new File(savePath));//将压缩后的图片保存的本地上指定路径中
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (bitmap != null) {
            bitmap.recycle();
        }
        return true; //压缩成功返回ture
    }

    /**
     * 根据分辨率压缩图片比例
     *
     * @param imgPath
     * @param w
     * @param h
     * @return
     */
    private static Bitmap compressByResolution(String imgPath, int w, int h) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imgPath, opts);

        int width = opts.outWidth;
        int height = opts.outHeight;
        int widthScale = width / w;
        int heightScale = height / h;

        int scale;
        if (widthScale < heightScale) { //保留压缩比例小的
            scale = widthScale;
        } else {
            scale = heightScale;
        }

        if (scale < 1) {
            scale = 1;
        }

        opts.inSampleSize = scale;

        opts.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, opts);

        return bitmap;
    }

    //获取默认 保存路径
    public static String getDefaultPicPath(Context context,String sourcePath) {
        String savePath;
        File filePic;
        String fileName = BitmapUtil.getPhotoNameWithType(sourcePath);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            savePath =  Environment.getExternalStorageDirectory() + SD_ROOT;
        } else {
            savePath = context.getApplicationContext().getFilesDir().getAbsolutePath();
        }
        try {
            filePic = new File(savePath,fileName);
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            fos.flush();
            fos.close();
            return filePic.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据图片的大小设置压缩的比例，提高速度
     *
     * @param imageMB
     * @return
     */
    private static int setSubstractSize(int imageMB) {

        if (imageMB > 1000) {
            return 40;
        } else if (imageMB > 750) {
            return 30;
        } else if (imageMB > 500) {
            return 20;
        } else {
            return 10;
        }
    }
}
