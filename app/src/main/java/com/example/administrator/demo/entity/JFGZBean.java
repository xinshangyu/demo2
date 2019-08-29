package com.example.administrator.demo.entity;

import java.io.Serializable;

public class JFGZBean implements Serializable {


    /**
     * protocolRules : {"id":"1","protocolType":"1","protocolName":"注册协议","protocolDescribe":"一带一路","protocolCount":"请遵守协议"}
     */

    private ProtocolRulesBean protocolRules;

    public ProtocolRulesBean getProtocolRules() {
        return protocolRules;
    }

    public void setProtocolRules(ProtocolRulesBean protocolRules) {
        this.protocolRules = protocolRules;
    }

    public static class ProtocolRulesBean {
        /**
         * id : 1
         * protocolType : 1
         * protocolName : 注册协议
         * protocolDescribe : 一带一路
         * protocolCount : 请遵守协议
         */

        private String id;
        private String protocolType;
        private String protocolName;
        private String protocolDescribe;
        private String protocolCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProtocolType() {
            return protocolType;
        }

        public void setProtocolType(String protocolType) {
            this.protocolType = protocolType;
        }

        public String getProtocolName() {
            return protocolName;
        }

        public void setProtocolName(String protocolName) {
            this.protocolName = protocolName;
        }

        public String getProtocolDescribe() {
            return protocolDescribe;
        }

        public void setProtocolDescribe(String protocolDescribe) {
            this.protocolDescribe = protocolDescribe;
        }

        public String getProtocolCount() {
            return protocolCount;
        }

        public void setProtocolCount(String protocolCount) {
            this.protocolCount = protocolCount;
        }
    }
}
