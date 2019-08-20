package com.example.administrator.demo.entity;

import java.io.Serializable;

public class TokenBen implements Serializable {

    /**
     * msg : 操作成功！
     * code : 200
     * data : {"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0MDI4ODA0MzZiODdkNDU3MDE2Yjg3ZmY4ODdjMDAwNiIsImV4cCI6MTU2MTQ2MzU1OCwidXNlcklkIjoiNDAyODgwNDM2Yjg3ZDQ1NzAxNmI4N2ZmODg3YzAwMDYiLCJpYXQiOjE1NjE0Mjc1NTgsInVzZXJDb2RlIjoiMTAwMiIsImp0aSI6ImYwY2I2NDM2LWQ2Y2EtNDNkNC05YjNhLTc0MTE0NGI1YTljOCJ9.q2UG373iJxQT4e1UMYhFoNcr2Ibx3SPrnpaQgjSvJFU"}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0MDI4ODA0MzZiODdkNDU3MDE2Yjg3ZmY4ODdjMDAwNiIsImV4cCI6MTU2MTQ2MzU1OCwidXNlcklkIjoiNDAyODgwNDM2Yjg3ZDQ1NzAxNmI4N2ZmODg3YzAwMDYiLCJpYXQiOjE1NjE0Mjc1NTgsInVzZXJDb2RlIjoiMTAwMiIsImp0aSI6ImYwY2I2NDM2LWQ2Y2EtNDNkNC05YjNhLTc0MTE0NGI1YTljOCJ9.q2UG373iJxQT4e1UMYhFoNcr2Ibx3SPrnpaQgjSvJFU
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
