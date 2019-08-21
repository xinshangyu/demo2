package com.example.baselibrary.zh.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Administrator on 2017/11/27 0027.
 * 图片加载工具类
 */

public class ImageLoader {
    public static ImageLoader mInstance = null;

    public static ImageLoader getInstance() {
        if (null == mInstance) {
            synchronized (ImageLoader.class) {
                if (null == mInstance) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }

    /**
     * 普通的加载图片 带占位图
     *
     * @param context
     * @param url
     * @param imageView
     * @param multiTransformation 同时设置scaleType在设置圆角或者圆形
     */
    public void loadingImage(Context context, final Object url, final ImageView imageView, MultiTransformation multiTransformation, int placeholder) {
        RequestOptions requestOptions = new RequestOptions();
        if (null != context) {
            if (placeholder != -1)
                requestOptions = requestOptions.placeholder(placeholder).error(placeholder);
            if (null != multiTransformation)
                requestOptions = requestOptions.transform(multiTransformation);
            Glide.with(context).load(url).apply(requestOptions).into(imageView);
        }
    }

    /**
     * 普通的加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public void loadingImage(Context context, final Object url, final ImageView imageView) {
        loadingImage(context, url, imageView, null, -1);
    }

    /**
     * 普通的加载图片 带展位图
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void loadImage(Context context, final Object url, final ImageView imageView, RequestOptions requestOptions, int placeholder) {
        if (null != context) {
            if (placeholder != -1)
                requestOptions = requestOptions.placeholder(placeholder).error(placeholder);
            Glide.with(context).load(url).apply(requestOptions).into(imageView);
        }
    }

    /**
     * 普通的加载图片 不带占位图的
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void loadImage(Context context, final Object url, final ImageView imageView) {
        loadImage(context, url, imageView, -1);
    }

    /**
     * 普通的加载图片 不带占位图的
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void loadImage(Context context, final Object url, final ImageView imageView, int placeholder) {
        RequestOptions requestOptions = new RequestOptions();
        loadImage(context, url, imageView, requestOptions, placeholder);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void loadCircleImage(Context context, Object url, ImageView imageView, int placeholder) {
        if (null != context) {
            loadImage(context, url, imageView, RequestOptions.circleCropTransform(), placeholder);
        }
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void loadCircleImage(Context context, Object url, ImageView imageView) {
        if (null != context) {
            loadCircleImage(context, url, imageView, -1);
        }
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param round
     * @param imageView
     */
    @Deprecated
    public void loadRoundImage(Context context, Object url, int round, ImageView imageView, int placeholder) {
        if (null != context) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.transform(new GlideRoundTransform(context, round));
            loadImage(context, url, imageView, requestOptions, placeholder);
        }
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param round
     * @param imageView
     */
    @Deprecated
    public void loadRoundImage(Context context, Object url, int round, ImageView imageView) {
        if (null != context) {
            loadRoundImage(context, url, round, imageView, -1);
        }
    }

    /**
     * 加载圆形带圆环图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void loadCircleWithBorderImage(Context context, Object url, int borderWidth, int borderColor, ImageView imageView, int placeholder) {
        if (null != context) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.transform(new GlideCircleWithBorder(context, borderWidth, ContextCompat.getColor(context, borderColor)));
            loadImage(context, url, imageView, requestOptions, placeholder);
        }
    }

    /**
     * 加载圆形带圆环图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void loadCircleWithBorderImage(Context context, Object url, int borderWidth, int borderColor, ImageView imageView) {
        if (null != context) {
            loadCircleWithBorderImage(context, url, borderWidth, borderColor, imageView, -1);
        }
    }


}
