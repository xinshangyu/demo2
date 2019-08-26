package com.example.baselibrary.zh.api;


public class ApiKeys {
    //开发模式   true内网，false外网
    public static final boolean DEBUG = true;

    /*=========================  内外  =======================*/

    public static final String API_DEVELOPMENT_DOMAIN = "http://192.168.0.195:8980/";//内网
    public static final String API_PRODUCTION_DOMAIN = "http://10.3.255.73:8080/";//外网
    public static final String API_PRODUCTION_DOMAIN_OUT_IMAGE = "http://192.168.0.195:8980/file/resourceFiles/download/image?";//外网图片


    /**
     * 访问接口的地址
     */
    public static final String API_DEVELOPMENT_URL = API_DEVELOPMENT_DOMAIN + "";//项目名
    public static final String API_PRODUCTION_URL = API_PRODUCTION_DOMAIN + "";
    public static final String API_PRODUCTION_URL_IMAGE = API_DEVELOPMENT_DOMAIN + ""; //项目名追加
    public static final String API_PRODUCTION_URL_IMAGE_OUT = API_PRODUCTION_DOMAIN_OUT_IMAGE + "";
    /**
     * 版本更新接口
     */
    public final static String API_UPDATE_VERSIONURL = "http://192.168.0.108:8083/qrcoder/query.do";

    public static final String getDomain() {
        return DEBUG ? API_DEVELOPMENT_DOMAIN : API_PRODUCTION_DOMAIN;
    }

    public static final String getApiUrl() {
        return DEBUG ? API_DEVELOPMENT_URL : API_PRODUCTION_URL;
    }

    public static final String getImageApiUrl() {
        return DEBUG ? API_PRODUCTION_URL_IMAGE : API_PRODUCTION_URL_IMAGE_OUT;
    }


}
