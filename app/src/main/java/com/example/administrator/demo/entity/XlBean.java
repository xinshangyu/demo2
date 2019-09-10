package com.example.administrator.demo.entity;

import java.io.Serializable;
import java.util.List;

public class XlBean implements Serializable {


    private List<EducationBackgroundBean> educationBackground;

    public List<EducationBackgroundBean> getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(List<EducationBackgroundBean> educationBackground) {
        this.educationBackground = educationBackground;
    }

    public static class EducationBackgroundBean {
        /**
         * id : c953a44facc24b7982d47796ce191695
         * typeCode : 1b158ea8cfa947fd95e78d7b517c99e9
         * code : man
         * codeValue : 博士
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
