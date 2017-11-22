package com.android.rdc.librarysystem.bean;

import java.util.Date;

/**
 * 读者类型
 */
public class ReaderType {

    private Long mId;
    private String mTypeName;
    private Long mBorrowCount;
    private Long mBorrowLen;
    private Date mE1xDate;//过期期限，也就是有效期
    private String mRemark;//备注

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getTypeName() {
        return mTypeName;
    }

    public void setTypeName(String typeName) {
        mTypeName = typeName;
    }

    public Long getBorrowCount() {
        return mBorrowCount;
    }

    public void setBorrowCount(Long borrowCount) {
        mBorrowCount = borrowCount;
    }

    public Long getBorrowLen() {
        return mBorrowLen;
    }

    public void setBorrowLen(Long borrowLen) {
        mBorrowLen = borrowLen;
    }

    public Date getE1xDate() {
        return mE1xDate;
    }

    public void setE1xDate(Date e1xDate) {
        mE1xDate = e1xDate;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

    @Override
    public String toString() {
        return "ReaderType{" +
                "mId=" + mId +
                ", mTypeName='" + mTypeName + '\'' +
                ", mBorrowCount=" + mBorrowCount +
                ", mBorrowLen=" + mBorrowLen +
                ", mE1xDate=" + mE1xDate +
                ", mRemark='" + mRemark + '\'' +
                '}';
    }
}
