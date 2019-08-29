package com.example.administrator.demo.entity;

import java.io.Serializable;

public class VersionBean implements Serializable {


    /**
     * appVersion : {"versionDescribe":"第一版","id":"0000001","versionClassify":"1","versionNumber":"v1.0.0","versionCode":"001","versionUrl":"c/c/c"}
     */

    private AppVersionBean appVersion;

    public AppVersionBean getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersionBean appVersion) {
        this.appVersion = appVersion;
    }

    public static class AppVersionBean {
        /**
         * versionDescribe : 第一版
         * id : 0000001
         * versionClassify : 1
         * versionNumber : v1.0.0
         * versionCode : 001
         * versionUrl : c/c/c
         */

        private String versionDescribe;
        private String id;
        private String versionClassify;
        private String versionNumber;
        private String versionCode;
        private String versionUrl;

        public String getVersionDescribe() {
            return versionDescribe;
        }

        public void setVersionDescribe(String versionDescribe) {
            this.versionDescribe = versionDescribe;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersionClassify() {
            return versionClassify;
        }

        public void setVersionClassify(String versionClassify) {
            this.versionClassify = versionClassify;
        }

        public String getVersionNumber() {
            return versionNumber;
        }

        public void setVersionNumber(String versionNumber) {
            this.versionNumber = versionNumber;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionUrl() {
            return versionUrl;
        }

        public void setVersionUrl(String versionUrl) {
            this.versionUrl = versionUrl;
        }
    }
}
