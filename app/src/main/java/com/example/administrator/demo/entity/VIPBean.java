package com.example.administrator.demo.entity;

import java.io.Serializable;
import java.util.List;

public class VIPBean implements Serializable {

    /**
     * userInfo : {"nickName":"银中","vipCode":"VIP0","vipEquitiesRule":[{"vipName":"402880ef6a14b2e1016a14da173e031","vipCode":"VIP0"},{"vipName":"402880ef6a14b2e1016a14da173e032","vipCode":"VIP1"},{"vipName":"402880f16b680ef0016b684d194f001f","vipCode":"VIP2"},{"vipName":"402880f16b680ef0016b684d429f0020","vipCode":"VIP3"},{"vipName":"402880f16b680ef0016b684d858f0021","vipCode":"VIP4"},{"vipName":"402880f16b680ef0016b684db3c60022","vipCode":"VIP5"},{"vipName":"402880f16b680ef0016b684df5bf0023","vipCode":"VIP6"},{"vipName":"402880f16b680ef0016b684e3b070024","vipCode":"VIP7"},{"vipName":"402880f16b680ef0016b684eb4b70025","vipCode":"VIP8"},{"vipName":"402880f16b680ef0016b684ef0570026","vipCode":"VIP9"},{"vipName":"402880ef6a14b2e1016a14da173e0312","vipCode":"VIP10"},{"vipName":"402880f1697ba95301697bc72a3c0000","vipCode":"VIP1"}]}
     */

    private UserInfoBean userInfo;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * nickName : 银中
         * vipCode : VIP0
         * vipEquitiesRule : [{"vipName":"402880ef6a14b2e1016a14da173e031","vipCode":"VIP0"},{"vipName":"402880ef6a14b2e1016a14da173e032","vipCode":"VIP1"},{"vipName":"402880f16b680ef0016b684d194f001f","vipCode":"VIP2"},{"vipName":"402880f16b680ef0016b684d429f0020","vipCode":"VIP3"},{"vipName":"402880f16b680ef0016b684d858f0021","vipCode":"VIP4"},{"vipName":"402880f16b680ef0016b684db3c60022","vipCode":"VIP5"},{"vipName":"402880f16b680ef0016b684df5bf0023","vipCode":"VIP6"},{"vipName":"402880f16b680ef0016b684e3b070024","vipCode":"VIP7"},{"vipName":"402880f16b680ef0016b684eb4b70025","vipCode":"VIP8"},{"vipName":"402880f16b680ef0016b684ef0570026","vipCode":"VIP9"},{"vipName":"402880ef6a14b2e1016a14da173e0312","vipCode":"VIP10"},{"vipName":"402880f1697ba95301697bc72a3c0000","vipCode":"VIP1"}]
         */

        private String nickName;
        private String vipCode;
        private String userPhoto;
        private String equitiesDescribe;
        private List<VipEquitiesRuleBean> vipEquitiesRule;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getVipCode() {
            return vipCode;
        }

        public void setVipCode(String vipCode) {
            this.vipCode = vipCode;
        }

        public List<VipEquitiesRuleBean> getVipEquitiesRule() {
            return vipEquitiesRule;
        }

        public void setVipEquitiesRule(List<VipEquitiesRuleBean> vipEquitiesRule) {
            this.vipEquitiesRule = vipEquitiesRule;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }

        public String getEquitiesDescribe() {
            return equitiesDescribe;
        }

        public void setEquitiesDescribe(String equitiesDescribe) {
            this.equitiesDescribe = equitiesDescribe;
        }

        public static class VipEquitiesRuleBean {
            /**
             * vipName : 402880ef6a14b2e1016a14da173e031
             * vipCode : VIP0
             */

            private String vipName;
            private String vipCode;
            private String vipCount;
            private String vipDecribe;

            public String getVipName() {
                return vipName;
            }

            public void setVipName(String vipName) {
                this.vipName = vipName;
            }

            public String getVipCode() {
                return vipCode;
            }

            public void setVipCode(String vipCode) {
                this.vipCode = vipCode;
            }

            public String getVipCount() {
                return vipCount;
            }

            public void setVipCount(String vipCount) {
                this.vipCount = vipCount;
            }

            public String getVipDecribe() {
                return vipDecribe;
            }

            public void setVipDecribe(String vipDecribe) {
                this.vipDecribe = vipDecribe;
            }
        }
    }
}
