package com.example.administrator.demo.entity;

import java.io.Serializable;
import java.util.List;

public class DaojuBean implements Serializable {


    private List<List<PropsAssetsBean>> propsAssets;

    public List<List<PropsAssetsBean>> getPropsAssets() {
        return propsAssets;
    }

    public static class PropsAssetsBean {
        /**
         * propName : 普通刷
         * propType : 1-1
         * propNumber : 83
         */

        private String propName;
        private String propType;
        private String propNumber;

        public String getPropName() {
            return propName;
        }

        public void setPropName(String propName) {
            this.propName = propName;
        }

        public String getPropType() {
            return propType;
        }

        public void setPropType(String propType) {
            this.propType = propType;
        }

        public String getPropNumber() {
            return propNumber;
        }

        public void setPropNumber(String propNumber) {
            this.propNumber = propNumber;
        }
    }
}
