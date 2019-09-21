package com.example.administrator.demo.entity;

import java.io.Serializable;

public class CodeBean implements Serializable {


    /**
     * code : 200
     * data : {"smsId":"402880436d534a6f016d53c117f90014"}
     * msg : 操作成功！
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * smsId : 402880436d534a6f016d53c117f90014
         */

        private String smsId;

        public String getSmsId() {
            return smsId;
        }

        public void setSmsId(String smsId) {
            this.smsId = smsId;
        }
    }
}
