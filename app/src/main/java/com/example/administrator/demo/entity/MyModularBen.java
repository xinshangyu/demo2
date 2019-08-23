package com.example.administrator.demo.entity;

import com.example.administrator.demo.network.result.WeatherResult;

import java.io.Serializable;

public class MyModularBen extends WeatherResult implements Serializable {


    /**
     * code : 0
     * data : {"userInfo":{"userSignature":"签名签名签名","petName":"汪汪汪","vipLevel":"VIP0","userPhoto":"402880ef6bb0ed2e016bd5e81e3d107d","butterfly":{"butterfly_tentacle":20000,"right_top_wing":8000,"butterfly_body":300000,"left_down_wing":9000,"left_top_wing":11000,"right_down_wing":10000,"id":"d28fe6b81d624c2b9fb56b0ff0aa634e","butterflyLevel":"5"}}}
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
         * userInfo : {"userSignature":"签名签名签名","petName":"汪汪汪","vipLevel":"VIP0","userPhoto":"402880ef6bb0ed2e016bd5e81e3d107d","butterfly":{"butterfly_tentacle":20000,"right_top_wing":8000,"butterfly_body":300000,"left_down_wing":9000,"left_top_wing":11000,"right_down_wing":10000,"id":"d28fe6b81d624c2b9fb56b0ff0aa634e","butterflyLevel":"5"}}
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
             * userSignature : 签名签名签名
             * petName : 汪汪汪
             * vipLevel : VIP0
             * userPhoto : 402880ef6bb0ed2e016bd5e81e3d107d
             * butterfly : {"butterfly_tentacle":20000,"right_top_wing":8000,"butterfly_body":300000,"left_down_wing":9000,"left_top_wing":11000,"right_down_wing":10000,"id":"d28fe6b81d624c2b9fb56b0ff0aa634e","butterflyLevel":"5"}
             */

            /**
             * "circleNumber": "455634", 
             * "attentionNumber": "78645", 
             * "fansNumber": "45654", 
             * "praiseNumber": "456453", 
             * "cityId": "e167ba040c295ecab825a61e65745159", 
             * "provinceId": "郑州", 
             * "nationId": "河南",
             */

            private String circleNumber;//商圈数量
            private String attentionNumber;//关注数量
            private String fansNumber;//粉丝数量
            private String praiseNumber;//点赞数量
            private String cityId;//城市
            private String provinceId;//省份
            private String nationId;//国家
            private String userSignature;
            private String petName;
            private String vipLevel;
            private String userPhoto;
            private ButterflyBean butterfly;

            public String getCircleNumber() {
                return circleNumber;
            }

            public void setCircleNumber(String circleNumber) {
                this.circleNumber = circleNumber;
            }

            public String getAttentionNumber() {
                return attentionNumber;
            }

            public void setAttentionNumber(String attentionNumber) {
                this.attentionNumber = attentionNumber;
            }

            public String getFansNumber() {
                return fansNumber;
            }

            public void setFansNumber(String fansNumber) {
                this.fansNumber = fansNumber;
            }

            public String getPraiseNumber() {
                return praiseNumber;
            }

            public void setPraiseNumber(String praiseNumber) {
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

            public String getNationId() {
                return nationId;
            }

            public void setNationId(String nationId) {
                this.nationId = nationId;
            }

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

            public ButterflyBean getButterfly() {
                return butterfly;
            }

            public void setButterfly(ButterflyBean butterfly) {
                this.butterfly = butterfly;
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
