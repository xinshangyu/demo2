package com.example.administrator.demo.entity;

import java.io.Serializable;
import java.util.List;

public class JyjlBean implements Serializable {


    private List<VirtualCurrencyRecordBean> virtualCurrencyRecord;

    public List<VirtualCurrencyRecordBean> getVirtualCurrencyRecord() {
        return virtualCurrencyRecord;
    }

    public void setVirtualCurrencyRecord(List<VirtualCurrencyRecordBean> virtualCurrencyRecord) {
        this.virtualCurrencyRecord = virtualCurrencyRecord;
    }

    public static class VirtualCurrencyRecordBean {
        /**
         * id : 402880436d5213f1016d5213f1550001
         * dealId : 402880436d4ec1e0016d5213f11f0003
         * dealCost : 100.00
         * surplusCost : 4300.00
         * dealWay : 购买书籍
         * createTime : 2019-09-21 12:28:22
         */

        private String id;
        private String dealId;
        private String dealCost;
        private String surplusCost;
        private String dealWay;
        private String createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDealId() {
            return dealId;
        }

        public void setDealId(String dealId) {
            this.dealId = dealId;
        }

        public String getDealCost() {
            return dealCost;
        }

        public void setDealCost(String dealCost) {
            this.dealCost = dealCost;
        }

        public String getSurplusCost() {
            return surplusCost;
        }

        public void setSurplusCost(String surplusCost) {
            this.surplusCost = surplusCost;
        }

        public String getDealWay() {
            return dealWay;
        }

        public void setDealWay(String dealWay) {
            this.dealWay = dealWay;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
