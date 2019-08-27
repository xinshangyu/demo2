package com.example.administrator.demo.entity;

import java.util.List;

public class SCBean {

    private List<BizCircleBean> bizCircle;

    public List<BizCircleBean> getBizCircle() {
        return bizCircle;
    }

    public void setBizCircle(List<BizCircleBean> bizCircle) {
        this.bizCircle = bizCircle;
    }

    public static class BizCircleBean {
        /**
         * commentNum : 0
         * userInfo : {"vipLevel":"VIP0","id":"402880436b9bb9a4016b9bdcdcfa0008","headPortrait":"402880ef6bb0ed2e016bd5e81e3d107d"}
         * createTime : 2019-07-17 11:47:33
         * praiseNum : 0
         * id : 713cd0d5dd471a0745b202f6f6dd19d5
         * userId : 402880436b9bb9a4016b9bdcdcfa0008
         * content : 今天下雨了
         */

        private String commentNum;
        private UserInfoBean userInfo;
        private String createTime;
        private String praiseNum;
        private String id;
        private String userId;
        private String content;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(String praiseNum) {
            this.praiseNum = praiseNum;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public static class UserInfoBean {
            /**
             * vipLevel : VIP0
             * id : 402880436b9bb9a4016b9bdcdcfa0008
             * headPortrait : 402880ef6bb0ed2e016bd5e81e3d107d
             */

            private String vipLevel;
            private String id;
            private String headPortrait;

            public String getVipLevel() {
                return vipLevel;
            }

            public void setVipLevel(String vipLevel) {
                this.vipLevel = vipLevel;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
            }
        }
    }
}
