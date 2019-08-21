package com.example.baselibrary.zh.net;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * json的Interceptor
 */

public class JsonInterceptor implements Interceptor {
    private static final String TAG = "JsonInterceptor";

    public JsonInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        //为了不消耗buffer，我们这里使用source先获得buffer对象，然后clone()后使用
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        //获得返回的数据
        Buffer buffer = source.buffer();
        //使用前clone()下，避免直接消耗
        String responseBodyStr = buffer.clone().readString(Charset.forName("UTF-8"));
        Log.i(TAG, "result-body= " + responseBodyStr);
        responseBody = ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), responseBodyStr);
        response = response.newBuilder().body(responseBody).build();
        Log.d(TAG, "result-body= " + response);
        return response;
    }
}
