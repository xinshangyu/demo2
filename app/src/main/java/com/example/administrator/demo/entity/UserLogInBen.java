package com.example.administrator.demo.entity;

import java.io.Serializable;
import java.util.List;

public class UserLogInBen implements Serializable {


    /**
     * code : 2021
     * data : {"adInfo":[{"id":"1123465","adName":"nihao","linkPath":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561522616391&di=563b024259da2cb4d028265b5ce17312&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180927%2F7af39c3813ec4ee4a23707fbcc8db148.jpeg","adSeat":"zhitou","fileId":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561522616391&di=563b024259da2cb4d028265b5ce17312&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180927%2F7af39c3813ec4ee4a23707fbcc8db148.jpeg"}],"userCode":{"id":"402880436b98447f016b98d0f69a0006","userCode":"13269978999","userPwd":"","userPhone":"13269978999","vipLevel":"vip1","isFirstLogin":"0"},"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0MDI4ODA0MzZiOTg0NDdmMDE2Yjk4ZDBmNjlhMDAwNiIsImV4cCI6MTU2MTY3Mjk3NCwidXNlcklkIjoiNDAyODgwNDM2Yjk4NDQ3ZjAxNmI5OGQwZjY5YTAwMDYiLCJpYXQiOjE1NjE2MzY5NzQsInVzZXJDb2RlIjoiMTMyNjk5Nzg5OTkiLCJqdGkiOiIzOGMyNmMzZC01YzA3LTRjMGItODQzOS00MWEyZGNlZWQ5MzUifQ.ORCGCgpmmPnwKU1KkkZLFo8ZnnWvFwRAvZKE0PC4l3w"}
     * msg : 恭喜您，登录成功！
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
         * adInfo : [{"id":"1123465","adName":"nihao","linkPath":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561522616391&di=563b024259da2cb4d028265b5ce17312&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180927%2F7af39c3813ec4ee4a23707fbcc8db148.jpeg","adSeat":"zhitou","fileId":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561522616391&di=563b024259da2cb4d028265b5ce17312&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180927%2F7af39c3813ec4ee4a23707fbcc8db148.jpeg"}]
         * userCode : {"id":"402880436b98447f016b98d0f69a0006","userCode":"13269978999","userPwd":"","userPhone":"13269978999","vipLevel":"vip1","isFirstLogin":"0"}
         * token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0MDI4ODA0MzZiOTg0NDdmMDE2Yjk4ZDBmNjlhMDAwNiIsImV4cCI6MTU2MTY3Mjk3NCwidXNlcklkIjoiNDAyODgwNDM2Yjk4NDQ3ZjAxNmI5OGQwZjY5YTAwMDYiLCJpYXQiOjE1NjE2MzY5NzQsInVzZXJDb2RlIjoiMTMyNjk5Nzg5OTkiLCJqdGkiOiIzOGMyNmMzZC01YzA3LTRjMGItODQzOS00MWEyZGNlZWQ5MzUifQ.ORCGCgpmmPnwKU1KkkZLFo8ZnnWvFwRAvZKE0PC4l3w
         */

        private UserCodeBean userCode;
        private String token;
        private List<AdInfoBean> adInfo;

        public UserCodeBean getUserCode() {
            return userCode;
        }

        public void setUserCode(UserCodeBean userCode) {
            this.userCode = userCode;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public List<AdInfoBean> getAdInfo() {
            return adInfo;
        }

        public void setAdInfo(List<AdInfoBean> adInfo) {
            this.adInfo = adInfo;
        }

        public static class UserCodeBean {
            /**
             * id : 402880436b98447f016b98d0f69a0006
             * userCode : 13269978999
             * userPwd :
             * userPhone : 13269978999
             * vipLevel : vip1
             * isFirstLogin : 0
             */

            private String id;
            private String userCode;
            private String userPwd;
            private String userPhone;
            private String vipLevel;
            private String isFirstLogin;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getUserPwd() {
                return userPwd;
            }

            public void setUserPwd(String userPwd) {
                this.userPwd = userPwd;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public String getVipLevel() {
                return vipLevel;
            }

            public void setVipLevel(String vipLevel) {
                this.vipLevel = vipLevel;
            }

            public String getIsFirstLogin() {
                return isFirstLogin;
            }

            public void setIsFirstLogin(String isFirstLogin) {
                this.isFirstLogin = isFirstLogin;
            }
        }

        public static class AdInfoBean {
            /**
             * id : 1123465
             * adName : nihao
             * linkPath : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561522616391&di=563b024259da2cb4d028265b5ce17312&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180927%2F7af39c3813ec4ee4a23707fbcc8db148.jpeg
             * adSeat : zhitou
             * fileId : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561522616391&di=563b024259da2cb4d028265b5ce17312&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180927%2F7af39c3813ec4ee4a23707fbcc8db148.jpeg
             */

            private String id;
            private String adName;
            private String linkPath;
            private String adSeat;
            private String fileId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAdName() {
                return adName;
            }

            public void setAdName(String adName) {
                this.adName = adName;
            }

            public String getLinkPath() {
                return linkPath;
            }

            public void setLinkPath(String linkPath) {
                this.linkPath = linkPath;
            }

            public String getAdSeat() {
                return adSeat;
            }

            public void setAdSeat(String adSeat) {
                this.adSeat = adSeat;
            }

            public String getFileId() {
                return fileId;
            }

            public void setFileId(String fileId) {
                this.fileId = fileId;
            }
        }
    }
}
