package com.example.administrator.demo.entity;

import java.io.Serializable;
import java.util.List;

public class DhBean implements Serializable {

    /**
     * msg : 操作成功！
     * code : 200
     * data : {"mallCommodity":[{"commodityId":"402880296c2c4563016c2c4563b20002","unitPrice":"1000","commodityName":null,"commodityImg":null,"commodityType":"1"}]}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MallCommodityBean> mallCommodity;

        public List<MallCommodityBean> getMallCommodity() {
            return mallCommodity;
        }

        public void setMallCommodity(List<MallCommodityBean> mallCommodity) {
            this.mallCommodity = mallCommodity;
        }

        public static class MallCommodityBean {
            /**
             * commodityId : 402880296c2c4563016c2c4563b20002
             * unitPrice : 1000
             * commodityName : null
             * commodityImg : null
             * commodityType : 1
             */

            private String commodityId;
            private String unitPrice;
            private String commodityName;
            private String commodityImg;
            private String commodityType;

            public String getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(String commodityId) {
                this.commodityId = commodityId;
            }

            public String getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(String unitPrice) {
                this.unitPrice = unitPrice;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getCommodityImg() {
                return commodityImg;
            }

            public void setCommodityImg(String commodityImg) {
                this.commodityImg = commodityImg;
            }

            public String getCommodityType() {
                return commodityType;
            }

            public void setCommodityType(String commodityType) {
                this.commodityType = commodityType;
            }
        }
    }
}
