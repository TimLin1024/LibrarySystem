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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookTypeStatisticBean that = (BookTypeStatisticBean) o;

        if (mBookTypeId != that.mBookTypeId) return false;
        if (mCount != that.mCount) return false;
        return mBookTypeName != null ? mBookTypeName.equals(that.mBookTypeName) : that.mBookTypeName == null;
    }

    @Override
    public int hashCode() {
        int result = mBookTypeName != null ? mBookTypeName.hashCode() : 0;
        result = 31 * result + (int) (mBookTypeId ^ (mBookTypeId >>> 32));
        result = 31 * result + mCount;
        return result;
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
