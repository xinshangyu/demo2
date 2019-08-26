package com.example.baselibrary.zh.network;


import com.example.baselibrary.zh.api.Address;
import com.example.baselibrary.zh.api.ApiKeys;
import com.example.baselibrary.SharedPreferencesHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class TokenHeaderInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder request = chain.request().newBuilder();
        request.addHeader("token", SharedPreferencesHelper.getPrefString("token", ""));
        Response proceed = chain.proceed(request.build());
        //如果token过期 再去重新请求token 然后设置token的请求头 重新发起请求 用户无感
        if (isTokenExpired(proceed)) {
            //使用新的Token，创建新的请求
            Request newRequest = chain.request().newBuilder()
                    .addHeader("token", RetrofitRequest.getNewToken(ApiKeys.getApiUrl() + Address.token,proceed))
                    .build();
            return chain.proceed(newRequest);
        }
        return proceed;

    }


    /**
     * 根据Response，判断Token是否失效
     * 401表示token过期
     *
     * @param response
     * @return
     */
    private boolean isTokenExpired(Response response) {
        if (response.code() == 401) {

            return true;
        }
        return false;
    }


}
