package com.example.administrator.demo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyDataBean implements Parcelable{
    private String name;
    private String path;
    private String newPath;
    private String size;
    private String id;
    private String src;
    private String type;
    private boolean isSelect;
    private List<FolderBean> folderBeans = new ArrayList<>();

    public MyDataBean(){}

    protected MyDataBean(Parcel in) {
        name = in.readString();
        path = in.readString();
        newPath = in.readString();
        size = in.readString();
        id = in.readString();
        src = in.readString();
        type = in.readString();
        isSelect = in.readByte() != 0;
        folderBeans = in.createTypedArrayList(FolderBean.CREATOR);
    }

    public static final Creator<MyDataBean> CREATOR = new Creator<MyDataBean>() {
        @Override
        public MyDataBean createFromParcel(Parcel in) {
            return new MyDataBean(in);
        }

        @Override
        public MyDataBean[] newArray(int size) {
            return new MyDataBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public List<FolderBean> getFolderBeans() {
        return folderBeans;
    }

    public void setFolderBeans(List<FolderBean> folderBeans) {
        this.folderBeans = folderBeans;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(path);
        dest.writeString(newPath);
        dest.writeString(size);
        dest.writeString(id);
        dest.writeString(src);
        dest.writeString(type);
        dest.writeByte((byte) (isSelect ? 1 : 0));
        dest.writeTypedList(folderBeans);
    }

    public static class FolderBean implements Parcelable{
        private String name;
        private String path;
        private String newPath;
        private String size;
        private String id;
        private String src;
        private String type;
        private boolean isSelect;

        public FolderBean(){}

        protected FolderBean(Parcel in) {
            name = in.readString();
            path = in.readString();
            newPath = in.readString();
            size = in.readString();
            id = in.readString();
            src = in.readString();
            type = in.readString();
            isSelect = in.readByte() != 0;
        }

        public static final Creator<FolderBean> CREATOR = new Creator<FolderBean>() {
            @Override
            public FolderBean createFromParcel(Parcel in) {
                return new FolderBean(in);
            }

            @Override
            public FolderBean[] newArray(int size) {
                return new FolderBean[size];
            }
        };

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getNewPath() {
            return newPath;
        }

        public void setNewPath(String newPath) {
            this.newPath = newPath;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(path);
            dest.writeString(newPath);
            dest.writeString(size);
            dest.writeString(id);
            dest.writeString(src);
            dest.writeString(type);
            dest.writeByte((byte) (isSelect ? 1 : 0));
        }
    }
}
