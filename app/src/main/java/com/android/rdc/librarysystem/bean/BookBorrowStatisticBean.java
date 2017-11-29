package com.android.rdc.librarysystem.bean;

public class BookBorrowStatisticBean {
    private boolean mIsBorrow;
    private int mCount;

    public boolean isBorrow() {
        return mIsBorrow;
    }

    public void setBorrow(boolean borrow) {
        mIsBorrow = borrow;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    @Override
    public String toString() {
        return "BookBorrowStatisticBean{" +
                "被借=" + mIsBorrow +
                ", 数量=" + mCount +
                '}';
    }
}
