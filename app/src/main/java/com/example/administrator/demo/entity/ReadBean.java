package com.example.administrator.demo.entity;

import java.io.Serializable;
import java.util.List;

public class ReadBean implements Serializable {


    private List<BookInfosBean> bookInfos;

    public List<BookInfosBean> getBookInfos() {
        return bookInfos;
    }

    public void setBookInfos(List<BookInfosBean> bookInfos) {
        this.bookInfos = bookInfos;
    }

    public static class BookInfosBean {
        /**
         * id : 402880ef6bb0ed2e016bd48bd0fe4fb6
         * datumId : 402880296d6326b1016d6326c49d0002
         * bookName : 伊朗：丝绸西路上的明珠
         * beforeCoverUrl : 402880ef6bb0ed2e016bd49e7aca7570
         */

        private String id;
        private String datumId;
        private String bookName;
        private String beforeCoverUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDatumId() {
            return datumId;
        }

        public void setDatumId(String datumId) {
            this.datumId = datumId;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getBeforeCoverUrl() {
            return beforeCoverUrl;
        }

        public void setBeforeCoverUrl(String beforeCoverUrl) {
            this.beforeCoverUrl = beforeCoverUrl;
        }
    }
}
