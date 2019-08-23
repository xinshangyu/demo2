package com.example.administrator.demo.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabBean implements CustomTabEntity {
    public String title;

    public TabBean(String title) {
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }

}
