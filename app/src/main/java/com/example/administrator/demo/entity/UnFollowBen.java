package com.example.administrator.demo.entity;

import java.io.Serializable;
import java.util.List;

public class UnFollowBen implements Serializable {


    private List<RelationRecordListBean> relationRecordList;

    public List<RelationRecordListBean> getRelationRecordList() {
        return relationRecordList;
    }

    public void setRelationRecordList(List<RelationRecordListBean> relationRecordList) {
        this.relationRecordList = relationRecordList;
    }

    public static class RelationRecordListBean {
        /**
         * id : f85401f306874d9d2a55c97206134332
         * userId : 402880296cd7af30016cdb2baaaa0010
         * fansId : 402880296cb71702016cb71702440001
         * ralationType : 1
         * userName : 王一一
         * nickName : 卡特
         * userPhoto : 402880266cd1c7a7016cd1c7bb5e001e
         * userSignature : 这就对了嘛
         */

        private String id;
        private String userId;
        private String fansId;
        private String ralationType;
        private String userName;
        private String nickName;
        private String userPhoto;
        private String userSignature;

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

        public String getFansId() {
            return fansId;
        }

        public void setFansId(String fansId) {
            this.fansId = fansId;
        }

        public String getRalationType() {
            return ralationType;
        }

        public void setRalationType(String ralationType) {
            this.ralationType = ralationType;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }

        public String getUserSignature() {
            return userSignature;
        }

        public void setUserSignature(String userSignature) {
            this.userSignature = userSignature;
        }
    }
}
