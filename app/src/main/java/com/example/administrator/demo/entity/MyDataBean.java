package com.example.administrator.demo.entity;

import java.io.Serializable;

public class MyDataBean {
    private String name;
    private String path;
    private String newPath;
    private String size;
    private String id;
    private String src;
    private boolean isSelect;

    public MyDataBean(){}

    public MyDataBean(String name, String path, String newPath, String size, String id, String src, boolean isSelect){
        this.name = name;
        this.path = path;
        this.newPath = newPath;
        this.size = size;
        this.id = id;
        this.src = src;
        this.isSelect = isSelect;
    }

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

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
