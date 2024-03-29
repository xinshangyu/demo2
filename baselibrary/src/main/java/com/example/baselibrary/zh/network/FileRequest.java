package com.example.baselibrary.zh.network;

import com.example.baselibrary.zh.net.CommonResponseBean;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 文件上传请求封装
 */
public interface FileRequest {

    /**
     * 上传文件请求
     *
     * @param url      URL路径
     * @param paramMap 请求参数
     * @return
     */
    @Multipart
    @POST
    Call<ResponseBody> postFile(@Url String url, @PartMap Map<String, RequestBody> paramMap);

    /**
     * 上传文件请求
     *
     * @param url      URL路径
     * @param paramMap 请求参数
     * @return
     */
    @Multipart
    @POST
    Call<ResponseBody> postFiles(@Url String url, @PartMap Map<String, List<RequestBody>> paramMap);


    /**
     * 下载文件get请求
     *
     * @param url 链接地址
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url);
}
