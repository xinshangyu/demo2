package com.example.administrator.demo.entity;

import com.example.baselibrary.zh.network.result.WeatherResult;

import java.io.Serializable;

/**
 * 客服bean
 */
public class HelpBean implements Serializable {


    /**
     * sysInfo : {"serviceTel":"0371-741741","officalAccounts":"xsy123456","serviceTime":"0:00-23:59"}
     */

    private SysInfoBean sysInfo;

    public SysInfoBean getSysInfo() {
        return sysInfo;
    }

    public void setSysInfo(SysInfoBean sysInfo) {
        this.sysInfo = sysInfo;
    }

    public static class SysInfoBean {
        /**
         * serviceTel : 0371-741741
         * officalAccounts : xsy123456
         * serviceTime : 0:00-23:59
         */

        private String serviceTel;
        private String officalAccounts;
        private String serviceTime;

        public String getServiceTel() {
            return serviceTel;
        }

        public void setServiceTel(String serviceTel) {
            this.serviceTel = serviceTel;
        }

        public String getOfficalAccounts() {
            return officalAccounts;
        }

        public void setOfficalAccounts(String officalAccounts) {
            this.officalAccounts = officalAccounts;
        }

        public String getServiceTime() {
            return serviceTime;
        }

        public void setServiceTime(String serviceTime) {
            this.serviceTime = serviceTime;
        }
    }
}
