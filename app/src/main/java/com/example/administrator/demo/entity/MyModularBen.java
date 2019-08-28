package com.example.administrator.demo.entity;

import java.io.Serializable;

public class MyModularBen implements Serializable {

    /**
     * code : 0
     * data : {"userInfo":{"userSignature":"天道酬勤","petName":"银中","vipLevel":"VIP0","userPhoto":"402880ef6bb0ed2e016bd5e81e3d107d","circleNumber":1,"fansNumber":1,"butterfly":{"butterfly_tentacle":20000,"right_top_wing":8000,"butterfly_body":300000,"left_down_wing":9000,"left_top_wing":11000,"right_down_wing":10000,"id":"d28fe6b81d624c2b9fb56b0ff0aa634e","butterflyLevel":"5"},"nationId":"6174accb8914403fbe47d438d721fb9e","praiseNumber":2,"cityId":"261746b632e8422bb488cd4fbff25057","provinceId":"a47078ef813541dd935b04a16523e457"}}
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
        /**
         * userInfo : {"userSignature":"天道酬勤","petName":"银中","vipLevel":"VIP0","userPhoto":"402880ef6bb0ed2e016bd5e81e3d107d","circleNumber":1,"fansNumber":1,"butterfly":{"butterfly_tentacle":20000,"right_top_wing":8000,"butterfly_body":300000,"left_down_wing":9000,"left_top_wing":11000,"right_down_wing":10000,"id":"d28fe6b81d624c2b9fb56b0ff0aa634e","butterflyLevel":"5"},"nationId":"6174accb8914403fbe47d438d721fb9e","praiseNumber":2,"cityId":"261746b632e8422bb488cd4fbff25057","provinceId":"a47078ef813541dd935b04a16523e457"}
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
             * userSignature : 天道酬勤
             * petName : 银中
             * vipLevel : VIP0
             * userPhoto : 402880ef6bb0ed2e016bd5e81e3d107d
             * circleNumber : 1.0
             * fansNumber : 1.0
             * butterfly : {"butterfly_tentacle":20000,"right_top_wing":8000,"butterfly_body":300000,"left_down_wing":9000,"left_top_wing":11000,"right_down_wing":10000,"id":"d28fe6b81d624c2b9fb56b0ff0aa634e","butterflyLevel":"5"}
             * nationId : 6174accb8914403fbe47d438d721fb9e
             * praiseNumber : 2.0
             * cityId : 261746b632e8422bb488cd4fbff25057
             * provinceId : a47078ef813541dd935b04a16523e457
             */

            private String userSignature;
            private String petName;
            private String vipLevel;
            private String userPhoto;
            private int circleNumber;
            private int fansNumber;
            private ButterflyBean butterfly;
            private String nationId;
            private int praiseNumber;
            private String cityId;
            private String provinceId;
            private int attentionNumber;

            public String getUserSignature() {
                return userSignature;
            }

            public void setUserSignature(String userSignature) {
                this.userSignature = userSignature;
            }

            public String getPetName() {
                return petName;
            }

            public void setPetName(String petName) {
                this.petName = petName;
            }

            public String getVipLevel() {
                return vipLevel;
            }

            public void setVipLevel(String vipLevel) {
                this.vipLevel = vipLevel;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public int getCircleNumber() {
                return circleNumber;
            }

            public void setCircleNumber(int circleNumber) {
                this.circleNumber = circleNumber;
            }

            public int getFansNumber() {
                return fansNumber;
            }

            public void setFansNumber(int fansNumber) {
                this.fansNumber = fansNumber;
            }

            public ButterflyBean getButterfly() {
                return butterfly;
            }

            public void setButterfly(ButterflyBean butterfly) {
                this.butterfly = butterfly;
            }

            public String getNationId() {
                return nationId;
            }

            public void setNationId(String nationId) {
                this.nationId = nationId;
            }

            public int getPraiseNumber() {
                return praiseNumber;
            }

            public void setPraiseNumber(int praiseNumber) {
                this.praiseNumber = praiseNumber;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(String provinceId) {
                this.provinceId = provinceId;
            }

            public int getAttentionNumber() {
                return attentionNumber;
            }

            public void setAttentionNumber(int attentionNumber) {
                this.attentionNumber = attentionNumber;
            }

            public static class ButterflyBean {
                /**
                 * butterfly_tentacle : 20000.0
                 * right_top_wing : 8000.0
                 * butterfly_body : 300000.0
                 * left_down_wing : 9000.0
                 * left_top_wing : 11000.0
                 * right_down_wing : 10000.0
                 * id : d28fe6b81d624c2b9fb56b0ff0aa634e
                 * butterflyLevel : 5
                 */

                private double butterfly_tentacle;
                private double right_top_wing;
                private double butterfly_body;
                private double left_down_wing;
                private double left_top_wing;
                private double right_down_wing;
                private String id;
                private String butterflyLevel;

                public double getButterfly_tentacle() {
                    return butterfly_tentacle;
                }

                public void setButterfly_tentacle(double butterfly_tentacle) {
                    this.butterfly_tentacle = butterfly_tentacle;
                }

                public double getRight_top_wing() {
                    return right_top_wing;
                }

                public void setRight_top_wing(double right_top_wing) {
                    this.right_top_wing = right_top_wing;
                }

                public double getButterfly_body() {
                    return butterfly_body;
                }

                public void setButterfly_body(double butterfly_body) {
                    this.butterfly_body = butterfly_body;
                }

                public double getLeft_down_wing() {
                    return left_down_wing;
                }

                public void setLeft_down_wing(double left_down_wing) {
                    this.left_down_wing = left_down_wing;
                }

                public double getLeft_top_wing() {
                    return left_top_wing;
                }

                public void setLeft_top_wing(double left_top_wing) {
                    this.left_top_wing = left_top_wing;
                }

                public double getRight_down_wing() {
                    return right_down_wing;
                }

                public void setRight_down_wing(double right_down_wing) {
                    this.right_down_wing = right_down_wing;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getButterflyLevel() {
                    return butterflyLevel;
                }

                public void setButterflyLevel(String butterflyLevel) {
                    this.butterflyLevel = butterflyLevel;
                }
            }
        }
    }
}
