package com.example.administrator.demo.entity;

import java.io.Serializable;
import java.util.List;

public class SexBean implements Serializable {


    private List<UserSexBean> userSex;

    public List<UserSexBean> getUserSex() {
        return userSex;
    }

    public void setUserSex(List<UserSexBean> userSex) {
        this.userSex = userSex;
    }

    public static class UserSexBean {
        /**
         * id : 483d35f4134c4e6ebeb21048f4bf9ee9
         * typeCode : 6e86f519257041cfaf00b0c8d7c6e60c
         * code : man
         * codeValue : 男
         */

        private String id;
        private String typeCode;
        private String code;
        private String codeValue;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCodeValue() {
            return codeValue;
        }

        public void setCodeValue(String codeValue) {
            this.codeValue = codeValue;
        }
    }
}
