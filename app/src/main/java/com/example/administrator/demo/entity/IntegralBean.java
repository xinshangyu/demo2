package com.example.administrator.demo.entity;

import java.io.Serializable;
import java.util.List;

public class IntegralBean implements Serializable {


    private List<IntegralRecordBean> integralRecord;

    public List<IntegralRecordBean> getIntegralRecord() {
        return integralRecord;
    }

    public void setIntegralRecord(List<IntegralRecordBean> integralRecord) {
        this.integralRecord = integralRecord;
    }

    public static class IntegralRecordBean {
        /**
         * commodityName : 俄罗斯东部陆海丝路的主枢纽
         * integralNumber : 100
         * createTime : 2019-09-12 14:57:27
         */

        private String commodityName;
        private int integralNumber;
        private String createTime;

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public int getIntegralNumber() {
            return integralNumber;
        }

        public void setIntegralNumber(int integralNumber) {
            this.integralNumber = integralNumber;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
