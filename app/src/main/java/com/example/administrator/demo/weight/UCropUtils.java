package com.example.administrator.demo.weight;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import com.example.administrator.demo.R;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;

/**
 * 文件 -- 图片裁剪工具类
 * @Description:
 * @Author: tao
 * @CreateDate: 2018/8/11 10:40
 */
public class UCropUtils {

    public static File getFileByUri(Uri uri, Context context) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA}, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2以后
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();

            return new File(path);
        } else {
//            Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }


    /**
     * 启动裁剪
     *
     * @param activity     上下文
     * @param sourceUir    需要裁剪图片的Uri
     * @param aspectRatioX 裁剪图片宽高比
     * @param aspectRatioY 裁剪图片宽高比
     * @return
     */
    public static void startUCrop(Activity activity, Uri sourceUir, float aspectRatioX, float aspectRatioY) {
        //裁剪后保存到文件中
        Uri destinationUri = Uri.fromFile(new File(activity.getCacheDir(), System.currentTimeMillis() + ".jpg"));
        //初始化，第一个参数：需要裁剪的图片；第二个参数：裁剪后图片
        UCrop uCrop = UCrop.of(sourceUir, destinationUri);
        //初始化UCrop配置
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures( UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置toolbar颜色
        options.setToolbarColor( ActivityCompat.getColor(activity, R.color.black));
        //设置状态栏颜色
        options.setStatusBarColor( ActivityCompat.getColor(activity, R.color.black));
        //是否能调整裁剪框
        //是否隐藏底部容器，默认显示
        options.setHideBottomControls(true);
//        options.setFreeStyleCropEnabled(true);
        uCrop.withOptions(options);
        //设置裁剪图片的宽高比，比如16：9（设置后就不能选择其他比例了、选择面板就不会出现了）
        uCrop.withAspectRatio(aspectRatioX, aspectRatioY);
        uCrop.start(activity);
    }

    /**
     * 启动裁剪
     *
     * @param sourceUir    需要裁剪图片的Uri
     * @param aspectRatioX 裁剪图片宽高比
     * @param aspectRatioY 裁剪图片宽高比
     * @return
     */
    public static void startFragmentUCrop(Context context, Fragment fragment, Uri sourceUir, float aspectRatioX, float aspectRatioY) {
        //裁剪后保存到文件中
        Uri destinationUri = Uri.fromFile(new File(context.getCacheDir(), System.currentTimeMillis() + ".jpg"));
        //初始化，第一个参数：需要裁剪的图片；第二个参数：裁剪后图片
        UCrop uCrop = UCrop.of(sourceUir, destinationUri);
        //初始化UCrop配置
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures( UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置toolbar颜色
        options.setToolbarColor( ActivityCompat.getColor(context, R.color.black));
        //设置状态栏颜色
        options.setStatusBarColor( ActivityCompat.getColor(context, R.color.black));
        //是否能调整裁剪框
        //是否隐藏底部容器，默认显示
        options.setHideBottomControls(true);
//        options.setFreeStyleCropEnabled(true);
        uCrop.withOptions(options);
        //设置裁剪图片的宽高比，比如16：9（设置后就不能选择其他比例了、选择面板就不会出现了）
        uCrop.withAspectRatio(aspectRatioX, aspectRatioY);
        uCrop.start(context, fragment);
    }

}
