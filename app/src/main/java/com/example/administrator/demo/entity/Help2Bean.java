package com.example.administrator.demo.entity;

import com.example.baselibrary.zh.network.result.WeatherResult;

import java.io.Serializable;
import java.util.List;

/**
 * 客服bean
 */
public class Help2Bean extends WeatherResult implements Serializable {


    private List<FaqRecordBean> faqRecord;

    public List<FaqRecordBean> getFaqRecord() {
        return faqRecord;
    }

    public void setFaqRecord(List<FaqRecordBean> faqRecord) {
        this.faqRecord = faqRecord;
    }

    public static class FaqRecordBean {
        /**
         * problemAnswers : 先领书在领宝箱
         * preblemTitle : 如何签到
         * id : 00001
         */

        private String problemAnswers;
        private String preblemTitle;
        private String id;

        public String getProblemAnswers() {
            return problemAnswers;
        }

        public void setProblemAnswers(String problemAnswers) {
            this.problemAnswers = problemAnswers;
        }

        public String getPreblemTitle() {
            return preblemTitle;
        }

        public void setPreblemTitle(String preblemTitle) {
            this.preblemTitle = preblemTitle;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
