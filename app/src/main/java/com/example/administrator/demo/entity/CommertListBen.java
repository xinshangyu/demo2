package com.example.administrator.demo.entity;

import java.io.Serializable;
import java.util.List;

public class CommertListBen implements Serializable {

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
         * userInfo : {"vipLevel":"VIP0","nickName":"银中","headPortrait":"402880ef6bb0ed2e016bd5e81e3d107d"}
         * createTime : 2019-07-29 11:56:27
         * praiseNum : 0
         * comment : {"id":"2583e9ac710bd4de7f6ad94ae199cce2","content":"暑假摸瞎"}
         * id : 2ef1f0943dd8d3a08fbe22b3868cc913
         * userId : 402880436b9bb9a4016b9bdcdcfa0008
         * content : 暑假快乐吧nnnnnnnnnnnn
         */

        private String commentNum;
        private UserInfoBean userInfo;
        private String createTime;
        private String praiseNum;
        private CommentBean comment;
        private String id;
        private String userId;
        private String content;
        private boolean isDetele;
        public boolean getIsDetele() {
            return isDetele;
        }

        public void setIsDetele(boolean isDetele) {
            this.isDetele = isDetele;
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

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
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
             * nickName : 银中
             * headPortrait : 402880ef6bb0ed2e016bd5e81e3d107d
             */

            private String vipLevel;
            private String nickName;
            private String headPortrait;

            public String getVipLevel() {
                return vipLevel;
            }

            public void setVipLevel(String vipLevel) {
                this.vipLevel = vipLevel;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
            }
        }

        public static class CommentBean {
            /**
             * id : 2583e9ac710bd4de7f6ad94ae199cce2
             * content : 暑假摸瞎
             */

            private String id;
            private String content;

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
        }
    }
}
