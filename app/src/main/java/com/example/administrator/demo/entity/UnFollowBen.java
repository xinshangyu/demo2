package com.example.administrator.demo.entity;

import com.example.baselibrary.zh.network.result.WeatherResult;

import java.io.Serializable;
import java.util.List;

public class UnFollowBen implements Serializable {


    /**
     * code : 200
     * data : {"userRelation":[{"ralationType":"0","userInfo":{"petName":"cccccc","userSignature":"我的状态","userPhoto":"402880ef6bb0ed2e016bd5e81e3d107d"},"id":"0359c8fda13a7098bfaaf51777024e3c","userId":"402880436bb26e6c016bb591be920008","beFocusedId":"402880436b9bb9a4016b9bdcdcfa0008"}]}
     * msg : success
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<UserRelationBean> userRelation;

        public List<UserRelationBean> getUserRelation() {
            return userRelation;
        }

        public void setUserRelation(List<UserRelationBean> userRelation) {
            this.userRelation = userRelation;
        }

        public static class UserRelationBean {
            /**
             * ralationType : 0
             * userInfo : {"petName":"cccccc","userSignature":"我的状态","userPhoto":"402880ef6bb0ed2e016bd5e81e3d107d"}
             * id : 0359c8fda13a7098bfaaf51777024e3c
             * userId : 402880436bb26e6c016bb591be920008
             * beFocusedId : 402880436b9bb9a4016b9bdcdcfa0008
             */

            private String ralationType;
            private UserInfoBean userInfo;
            private String id;
            private String userId;
            private String beFocusedId;

            public String getRalationType() {
                return ralationType;
            }

            public void setRalationType(String ralationType) {
                this.ralationType = ralationType;
            }

            public UserInfoBean getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfoBean userInfo) {
                this.userInfo = userInfo;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getBeFocusedId() {
                return beFocusedId;
            }

            public void setBeFocusedId(String beFocusedId) {
                this.beFocusedId = beFocusedId;
            }

            public static class UserInfoBean {
                /**
                 * petName : cccccc
                 * userSignature : 我的状态
                 * userPhoto : 402880ef6bb0ed2e016bd5e81e3d107d
                 */

                private String petName;
                private String userSignature;
                private String userPhoto;
                private String vipLevel;

                public String getPetName() {
                    return petName;
                }

                public void setPetName(String petName) {
                    this.petName = petName;
                }

                public String getUserSignature() {
                    return userSignature;
                }

                public void setUserSignature(String userSignature) {
                    this.userSignature = userSignature;
                }

                public String getUserPhoto() {
                    return userPhoto;
                }

                public void setUserPhoto(String userPhoto) {
                    this.userPhoto = userPhoto;
                }

                public String getVipLevel() {
                    return vipLevel;
                }

                public void setVipLevel(String vipLevel) {
                    this.vipLevel = vipLevel;
                }
            }
        }
    }
}
