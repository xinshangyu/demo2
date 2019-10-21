package com.example.baselibrary.zh.network;

import android.content.Context;
import android.os.Environment;

import com.example.baselibrary.zh.bean.TokenBen;
import com.example.baselibrary.zh.network.result.BaseResult;
import com.example.baselibrary.zh.network.util.Constant;
import com.example.baselibrary.zh.network.util.NetworkUtil;
import com.example.baselibrary.LogUtil;
import com.example.baselibrary.SharedPreferencesHelper;
import com.example.baselibrary.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Retrofit请求类
 */
public class RetrofitRequest {
    private static int TIME_OUT = 30; // 30秒超时断开连接
    // 自定义一个信任所有证书的TrustManager，添加SSLSocketFactory的时候要用到
    private static X509TrustManager trustAllCert = new X509TrustManager() {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }
    };
    private static SSLSocketFactory sslSocketFactory = new SSLSocketFactoryCompat(trustAllCert);

    // httpclient
    public static OkHttpClient client = new OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustAllCert)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(new TokenHeaderInterceptor())
            .build();

    /**
     * 网络框架单例
     * 设置为根路径即可
     */
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.URL_BASE)
            .client(client)
            .build();


    /**
     * 返回新Token
     **/

    public static String getNewToken(String url, okhttp3.Response response) {
        response.close();
        GetRequest getRequest = retrofit.create(GetRequest.class);
        // 构建请求
        Call<ResponseBody> call = getRequest.getUrl(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    ResponseBody body = response.body();

                    TokenBen tokenBen = new Gson().fromJson(body.string(), TokenBen.class);
                    SharedPreferencesHelper.setPrefString("token", tokenBen.getData().getToken());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return SharedPreferencesHelper.getPrefString("token", "");
    }


    /**
     * 发送GET网络请求
     *
     * @param url           请求地址
     * @param clazz         返回的数据类型
     * @param resultHandler 回调
     * @param <T>           泛型
     */
    public static <T extends BaseResult> void sendGetRequest(String url, final Class<T> clazz, final ResultHandler<T> resultHandler) {
        // 判断网络连接状况
        if (resultHandler.isNetDisconnected()) {
            resultHandler.onAfterFailure();
            return;
        }
        GetRequest getRequest = retrofit.create(GetRequest.class);
        // 构建请求
        Call<ResponseBody> call = getRequest.getUrl(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                resultHandler.onBeforeResult();
                try {

                    ResponseBody body = response.body();
                    if (body == null) {
                        resultHandler.onServerError();
                        return;
                    }
                    String string = body.string();
                    T t = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().fromJson(string, clazz);

                    resultHandler.onResult(t);
                } catch (IOException e) {
                    e.printStackTrace();
                    resultHandler.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                resultHandler.onFailure(t);
                resultHandler.onAfterFailure();
            }
        });
    }

    /**
     * 发送post网络请求
     *
     * @param url           请求地址
     * @param paramMap      参数列表
     * @param clazz         返回的数据类型
     * @param resultHandler 回调
     * @param <T>           泛型
     */
    public static <T extends BaseResult> void sendPostRequest(String url, Map<String, String> paramMap, final Class<T> clazz, final ResultHandler<T> resultHandler) {
        // 判断网络连接状况
        if (resultHandler.isNetDisconnected()) {
            resultHandler.onAfterFailure();
            return;
        }
        PostRequest postRequest = retrofit.create(PostRequest.class);

        // 构建请求
        Call<ResponseBody> call = postRequest.postMap(url, paramMap);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                resultHandler.onBeforeResult();

                try {

                    ResponseBody body = response.body();
                    if (body == null) {
                        resultHandler.onServerError();
                        return;
                    }
                    String string = body.string();
                    T t = new Gson().fromJson(string, clazz);

                    resultHandler.onResult(t);
                } catch (IOException e) {
                    e.printStackTrace();
                    resultHandler.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                resultHandler.onFailure(t);
                resultHandler.onAfterFailure();
            }
        });
    }

    /**
     * 发送上传文件网络请求
     *
     * @param url           请求地址
     * @param file          文件
     * @param clazz         返回的数据类型
     * @param resultHandler 回调
     * @param <T>           泛型
     */
    public static <T extends BaseResult> void fileUpload(String url, File file, final Class<T> clazz, final ResultHandler<T> resultHandler) {
        // 判断网络连接状况
        if (resultHandler.isNetDisconnected()) {
            resultHandler.onAfterFailure();
            return;
        }
        FileRequest fileRequest = retrofit.create(FileRequest.class);

        Map<String, RequestBody> paramMap = new HashMap<>();

        addMultiPart(paramMap, "file", file);

        // 构建请求
        Call<ResponseBody> call = fileRequest.postFile(url, paramMap);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                resultHandler.onBeforeResult();
                try {
                    ResponseBody body = response.body();
                    if (body == null) {
                        resultHandler.onServerError();
                        return;
                    }
                    String string = body.string();
                    T t = new Gson().fromJson(string, clazz);

                    resultHandler.onResult(t);
                } catch (IOException e) {
                    e.printStackTrace();
                    resultHandler.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                resultHandler.onFailure(t);
                resultHandler.onAfterFailure();
            }
        });
    }

    public static <T extends BaseResult> void fileUploads(String url, List<File> files, final Class<T> clazz, final ResultHandler<T> resultHandler) {
        // 判断网络连接状况
        if (resultHandler.isNetDisconnected()) {
            resultHandler.onAfterFailure();
            return;
        }
        FileRequest fileRequest = retrofit.create(FileRequest.class);

        Map<String, RequestBody> paramMap = new HashMap<>();

        addMultiParts(paramMap, "file", files);

        // 构建请求
        Call<ResponseBody> call = fileRequest.postFile(url, paramMap);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                resultHandler.onBeforeResult();
                try {
                    ResponseBody body = response.body();
                    if (body == null) {
                        resultHandler.onServerError();
                        return;
                    }
                    String string = body.string();
                    T t = new Gson().fromJson(string, clazz);

                    resultHandler.onResult(t);
                } catch (IOException e) {
                    e.printStackTrace();
                    resultHandler.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                resultHandler.onFailure(t);
                resultHandler.onAfterFailure();
            }
        });
    }

    /**
     * 文件下载
     *
     * @param url             请求地址
     * @param downloadHandler 回调接口
     */
    public static void fileDownload(String url, final DownloadHandler downloadHandler) {
        // 回调方法执行器，定义回调在子线程中执行，避免Callback返回到MainThread，导致文件下载出现NetworkOnMainThreadException
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        // 网络框架
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .callbackExecutor(executorService)
                .build();

        FileRequest fileRequest = retrofit.create(FileRequest.class);
        // 构建请求
        Call<ResponseBody> call = fileRequest.download(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // 写入文件
                    downloadHandler.onBody(response.body());
                    writeResponseBodyToDisk(response.body(), downloadHandler);

                } else {
                    downloadHandler.onError();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                downloadHandler.onError();
            }
        });
    }

    /**
     * 添加多媒体类型
     *
     * @param paramMap 参数对
     * @param key      键
     * @param obj      值
     */
    private static void addMultiPart(Map<String, RequestBody> paramMap, String key, Object obj) {
        if (obj instanceof String) {
            RequestBody body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) obj);
            paramMap.put(key, body);
        } else if (obj instanceof File) {
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), (File) obj);
            paramMap.put(key + "\"; filename=\"" + ((File) obj).getName() + "", body);
        }
    }

    /**
     * 添加多媒体类型
     *
     * @param paramMap 参数对
     * @param key      键
     */
    private static void addMultiParts(Map<String, RequestBody> paramMap, String key, List<File> files) {
        for (int i = 0; i < files.size(); i++) {
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), files.get(i));
            paramMap.put(key + "\"; filename=\"" + files.get(i).getName() + "", body);
        }
    }

    /**
     * 网络请求结果处理类
     *
     * @param <T> 请求结果封装对象
     */
    public static abstract class ResultHandler<T> {
        Context context;

        public ResultHandler(Context context) {
            this.context = context;
        }

        /**
         * 判断网络是否未连接
         *
         * @return
         */
        public boolean isNetDisconnected() {
            return NetworkUtil.isNetDisconnected(context);
        }

        /**
         * 请求成功之前
         */
        public abstract void onBeforeResult();

        /**
         * 请求成功时
         *
         * @param t 结果数据
         */
        public abstract void onResult(T t);

        /**
         * 服务器出错
         */
        public void onServerError() {
            // 服务器处理出错
            ToastUtils.showShort(context, "服务器处理出错");
        }

        /**
         * 请求失败后的处理
         */
        public abstract void onAfterFailure();

        /**
         * 请求失败时的处理
         *
         * @param t
         */
        public void onFailure(Throwable t) {
            if (t instanceof SocketTimeoutException || t instanceof ConnectException) {
                // 连接异常
                if (NetworkUtil.isNetworkConnected(context)) {
                    // 服务器连接出错
                    ToastUtils.showShort(context, "服务器连接出错，请稍后重试");
                    //  ToastUtils.showShort(context, R.string.net_server_connected_error);
                } else {
                    ToastUtils.showShort(context, "网络连接失败，请稍后重试");
                    // 手机网络不通
                    //   ToastUtils.showShort(context, R.string.net_not_connected);
                }
            } else if (t instanceof Exception) {
                // 功能异常
                ToastUtils.showShort(context, "未知错误，请稍后重试");
                LogUtil.e("功能异常" + t.toString());
            }
        }
    }

    /**
     * 文件下载回调
     */
    public interface DownloadHandler {
        /**
         * 接收到数据体
         *
         * @param body 响应体
         */
        void onBody(ResponseBody body);

        /**
         * 下载进度
         *
         * @param progress 当前进度
         */
        void onProgress(int progress);

        /**
         * 下载完成
         *
         * @param file 下载成功文件
         */
        void onDownLoadSuccess(File file);

        /**
         * 文件下载出错
         */
        void onError();
    }

    /**
     * 下载到本地
     *
     * @param body            内容
     * @param downloadHandler
     * @return 成功或者失败
     */
    public static boolean writeResponseBodyToDisk(ResponseBody body, DownloadHandler downloadHandler) {
        try {
            //判断文件夹是否存在
            File files = new File(getCacheMD());//跟目录一个文件夹
            if (!files.exists()) {
                //不存在就创建出来
                files.mkdirs();
            }
            //创建一个文件
            File futureStudioIconFile = new File(getCacheMD() + "xinshangyu.apk");
            //初始化输入流
            InputStream inputStream = null;
            //初始化输出流
            OutputStream outputStream = null;
            try {
                //设置每次读写的字节
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                //请求返回的字节流
                inputStream = body.byteStream();
                //创建输出流
                outputStream = new FileOutputStream(futureStudioIconFile);
                //进行读取操作
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    //进行写入操作
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    if (downloadHandler != null) {
                        int progress = (int) (fileSizeDownloaded * 1.0f / fileSize * 100);
                        downloadHandler.onProgress(progress);
                    }
                }

                //刷新
                outputStream.flush();
                downloadHandler.onDownLoadSuccess(futureStudioIconFile);
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    //关闭输入流
                    inputStream.close();
                }
                if (outputStream != null) {
                    //关闭输出流
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 最外层目录
     **/
    public static String SYS_TEMMD = "xinshangyu";

    /**
     * 缓存目录
     **/
    public static String CACHE = "cache";

    /**
     * 得到缓存目录
     *
     * @param
     * @return
     */
    public static String getCacheMD() {
        String sdcard = Environment.getExternalStorageDirectory().toString();
        File file = new File(sdcard + "/" + SYS_TEMMD);
        if (!file.exists())
            file.mkdir();
        File file2 = new File(sdcard + "/" + SYS_TEMMD + "/" + CACHE);
        if (!file2.exists())
            file2.mkdir();
        return sdcard + "/" + SYS_TEMMD + "/" + CACHE + "/";
    }
}
