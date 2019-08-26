package com.example.administrator.demo.entity;

import java.io.Serializable;

/**关注/粉丝**/
public class UserFollowBen implements Serializable {

    /**
     * userRelation : {"id":"118d2836f92c53069e8d1035b477028c","beFocusedId":"8e9ded1b52af5a8a97d76275a1132e6f","ralationType":"1","userId":"46380b3727ac52a59e410deef54b4cdc","userInfo":{"userSignature":"签名好难额","userPhoto":"http://img2.imgtn.bdimg.com/it/u=884653224,789685640&fm=26&gp=0.jpg","petName":"出彩河南"}}
     */

    private UserRelationBean userRelation;

    public UserRelationBean getUserRelation() {
        return userRelation;
    }

    public void setUserRelation(UserRelationBean userRelation) {
        this.userRelation = userRelation;
    }

    public static class UserRelationBean {
        /**
         * id : 118d2836f92c53069e8d1035b477028c
         * beFocusedId : 8e9ded1b52af5a8a97d76275a1132e6f
         * ralationType : 1
         * userId : 46380b3727ac52a59e410deef54b4cdc
         * userInfo : {"userSignature":"签名好难额","userPhoto":"http://img2.imgtn.bdimg.com/it/u=884653224,789685640&fm=26&gp=0.jpg","petName":"出彩河南"}
         */

        private String id;
        private String beFocusedId;
        private String ralationType;
        private String userId;
        private UserInfoBean userInfo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBeFocusedId() {
            return beFocusedId;
        }

        public void setBeFocusedId(String beFocusedId) {
            this.beFocusedId = beFocusedId;
        }

        public String getRalationType() {
            return ralationType;
        }

        public void setRalationType(String ralationType) {
            this.ralationType = ralationType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * userSignature : 签名好难额
             * userPhoto : http://img2.imgtn.bdimg.com/it/u=884653224,789685640&fm=26&gp=0.jpg
             * petName : 出彩河南
             */

            private String userSignature;
            private String userPhoto;
            private String petName;

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

            public String getPetName() {
                return petName;
            }

            public void setPetName(String petName) {
                this.petName = petName;
            }
        }
    }
}
