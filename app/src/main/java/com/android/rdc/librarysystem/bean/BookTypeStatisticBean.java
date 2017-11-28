package com.android.rdc.librarysystem.bean;

public class BookTypeStatisticBean {
    private String mBookTypeName;
    private long mBookTypeId;
    private int mCount;

    public String getBookTypeName() {
        return mBookTypeName;
    }
    public void setBookTypeName(String bookTypeName) {

        mBookTypeName = bookTypeName;
    }

    public long getBookTypeId() {
        return mBookTypeId;
    }

    public void setBookTypeId(long bookTypeId) {
        mBookTypeId = bookTypeId;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    @Override
    public String toString() {
        return "BookTypeStatisticBean{" +
                "类型名称： '" + mBookTypeName +
                ", 类型Id：" + mBookTypeId +
                ", 类型数量：" + mCount +
                '}';
    }
}
