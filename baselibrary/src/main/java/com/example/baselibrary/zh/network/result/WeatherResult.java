package com.example.baselibrary.zh.network.result;

import com.google.gson.JsonArray;

public class WeatherResult extends BaseResult {
    private int code;
    private String msg;
    private Object data; // 数据部分也是一个bean，用JsonObject代替了

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(JsonArray data) {
        this.data = data;
    }
}
