package com.example.administrator.demo.entity;

import java.io.Serializable;

public class UpdateUserInfoBean implements Serializable {


    /**
     * userInfo : {"id":"402880296cb71702016cb71702440001","userPhoto":"402880266cd1c7a7016cd1c7bbb20020","userName":"刘超","nickName":"liuchao","userSex":"483d35f4134c4e6ebeb21048f4bf9ee9","graduationSchool":"河南农业大学","educationBackground":"8886ab0f45ef4342a61e99d7683ee1a4","profession":"软件工程","homeSite":"河南郑州","residenceSite":null,"userCode":null,"orgInfo":{"companyId":"000000002","companyName":"黑猫教育资讯合伙企业","deptId":"0000000","deptName":"国家安全部"}}
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
         * id : 402880296cb71702016cb71702440001
         * userPhoto : 402880266cd1c7a7016cd1c7bbb20020
         * userName : 刘超
         * nickName : liuchao
         * userSex : 483d35f4134c4e6ebeb21048f4bf9ee9
         * graduationSchool : 河南农业大学
         * educationBackground : 8886ab0f45ef4342a61e99d7683ee1a4
         * profession : 软件工程
         * homeSite : 河南郑州
         * residenceSite : null
         * userCode : null
         * orgInfo : {"companyId":"000000002","companyName":"黑猫教育资讯合伙企业","deptId":"0000000","deptName":"国家安全部"}
         */

        private String id;
        private String userPhoto;
        private String userName;
        private String nickName;
        private String userSex;
        private String graduationSchool;
        private String educationBackground;
        private String profession;
        private String homeSite;
        private Object residenceSite;
        private Object userCode;
        private OrgInfoBean orgInfo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
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

        public String getUserSex() {
            return userSex;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
        }

        public String getGraduationSchool() {
            return graduationSchool;
        }

        public void setGraduationSchool(String graduationSchool) {
            this.graduationSchool = graduationSchool;
        }

        public String getEducationBackground() {
            return educationBackground;
        }

        public void setEducationBackground(String educationBackground) {
            this.educationBackground = educationBackground;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getHomeSite() {
            return homeSite;
        }

        public void setHomeSite(String homeSite) {
            this.homeSite = homeSite;
        }

        public Object getResidenceSite() {
            return residenceSite;
        }

        public void setResidenceSite(Object residenceSite) {
            this.residenceSite = residenceSite;
        }

        public Object getUserCode() {
            return userCode;
        }

        public void setUserCode(Object userCode) {
            this.userCode = userCode;
        }

        public OrgInfoBean getOrgInfo() {
            return orgInfo;
        }

        public void setOrgInfo(OrgInfoBean orgInfo) {
            this.orgInfo = orgInfo;
        }

        public static class OrgInfoBean {
            /**
             * companyId : 000000002
             * companyName : 黑猫教育资讯合伙企业
             * deptId : 0000000
             * deptName : 国家安全部
             */

            private String companyId;
            private String companyName;
            private String deptId;
            private String deptName;

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getDeptId() {
                return deptId;
            }

            public void setDeptId(String deptId) {
                this.deptId = deptId;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }
        }
    }
}
