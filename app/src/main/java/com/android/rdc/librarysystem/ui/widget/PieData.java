package com.android.rdc.librarysystem.ui.widget;

public class PieData {
    //用户关心的数据
    private String mName;
    private float mValue;
    private float mPercentage;

    //非用户关心的数据
    private int mColor;//颜色
    private float mAngle;//角度

    public PieData(String name, float value) {
        mName = name;
        mValue = value;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getValue() {
        return mValue;
    }

    public void setValue(float value) {
        mValue = value;
    }

    public float getPercentage() {
        return mPercentage;
    }

    public void setPercentage(float percentage) {
        mPercentage = percentage;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }
}
