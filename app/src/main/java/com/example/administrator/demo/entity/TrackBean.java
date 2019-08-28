package com.example.administrator.demo.entity;

import java.io.Serializable;
import java.util.List;

public class TrackBean implements Serializable{

    private List<FootprintBean> footprint;

    public List<FootprintBean> getFootprint() {
        return footprint;
    }

    public void setFootprint(List<FootprintBean> footprint) {
        this.footprint = footprint;
    }

    public static class FootprintBean {
        private String footprintOrder;
        private String footprintName;
        private String id;
        private String footprintDescribe;
        private String footprintImgSrc;
        private boolean add;

        public String getFootprintOrder() {
            return footprintOrder;
        }

        public void setFootprintOrder(String footprintOrder) {
            this.footprintOrder = footprintOrder;
        }

        public String getFootprintName() {
            return footprintName;
        }

        public void setFootprintName(String footprintName) {
            this.footprintName = footprintName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFootprintDescribe() {
            return footprintDescribe;
        }

        public void setFootprintDescribe(String footprintDescribe) {
            this.footprintDescribe = footprintDescribe;
        }

        public String getFootprintImgSrc() {
            return footprintImgSrc;
        }

        public void setFootprintImgSrc(String footprintImgSrc) {
            this.footprintImgSrc = footprintImgSrc;
        }

        public boolean isAdd() {
            return add;
        }

        public void setAdd(boolean add) {
            this.add = add;
        }
    }
}