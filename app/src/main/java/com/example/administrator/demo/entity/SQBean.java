package com.example.administrator.demo.entity;

import com.example.baselibrary.zh.network.result.WeatherResult;

import java.util.List;

/**
 * 商圈bean
 */
public class SQBean extends WeatherResult {


    private List<BizCircleBean> bizCircle;

    public List<BizCircleBean> getBizCircle() {
        return bizCircle;
    }

    public void setBizCircle(List<BizCircleBean> bizCircle) {
        this.bizCircle = bizCircle;
    }

    public static class BizCircleBean {
        /**
         * transpondNum : 1
         * commentNum : 0
         * userInfo : {"userPhoto":"402880ef6bb0ed2e016bd5e81e3d107d","nickName":"银中","id":"402880436b9bb9a4016b9bdcdcfa0008"}
         * durationTime : 918
         * createTime : 2019-08-12 11:35:26
         * priseNum : 1
         * collectNum : 1
         * isPublic : 0
         * id : 36951076edb93c38fae443b25ad56865
         * content : 银中是个大xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
         */

        private String transpondNum;
        private String commentNum;
        private UserInfoBean userInfo;
        private String durationTime;
        private String createTime;
        private String priseNum;
        private String collectNum;
        private String isPublic;
        private String id;
        private String content;

        public String getTranspondNum() {
            return transpondNum;
        }

        public void setTranspondNum(String transpondNum) {
            this.transpondNum = transpondNum;
        }

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public String getDurationTime() {
            return durationTime;
        }

        public void setDurationTime(String durationTime) {
            this.durationTime = durationTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPriseNum() {
            return priseNum;
        }

        public void setPriseNum(String priseNum) {
            this.priseNum = priseNum;
        }

        public String getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(String collectNum) {
            this.collectNum = collectNum;
        }

        public String getIsPublic() {
            return isPublic;
        }

        public void setIsPublic(String isPublic) {
            this.isPublic = isPublic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public static class UserInfoBean {
            /**
             * userPhoto : 402880ef6bb0ed2e016bd5e81e3d107d
             * nickName : 银中
             * id : 402880436b9bb9a4016b9bdcdcfa0008
             */

            private String userPhoto;
            private String nickName;
            private String id;

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
