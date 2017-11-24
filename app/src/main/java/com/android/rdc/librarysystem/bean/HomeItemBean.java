package com.android.rdc.librarysystem.bean;

public class HomeItemBean {
    private String mItemName;
    private int mIconId;

    public HomeItemBean() {
    }

    public HomeItemBean(String itemName, int iconId) {
        mItemName = itemName;
        mIconId = iconId;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int iconId) {
        mIconId = iconId;
    }
}
