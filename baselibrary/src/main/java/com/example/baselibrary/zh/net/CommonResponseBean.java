package com.example.baselibrary.zh.net;

import java.io.Serializable;

/**
 * 返回通用的实体类
 */

public class CommonResponseBean<T> implements Serializable {
    private T data;
    private int code;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return code == 200;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String info) {
        this.msg = info;
    }
}
