package com.android.rdc.librarysystem.bean;

import java.util.Date;

/**
 * 读者类型
 */
public class ReaderBean {

    private Long mId;
    private String mName;
    private ReaderType mReaderType;
    private String mCompany;//工作单位
    private String mGender;//性别
    private String mAddress;//家庭地址
    private String mPhoneNum;//电话号码
    private String mEmalil;//邮箱
    private Date mEnrollDate;//登记日期
    private String mRemark;//备注

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public ReaderType getReaderType() {
        return mReaderType;
    }

    public void setReaderType(ReaderType readerType) {
        mReaderType = readerType;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getPhoneNum() {
        return mPhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        mPhoneNum = phoneNum;
    }

    public String getEmalil() {
        return mEmalil;
    }

    public void setEmalil(String emalil) {
        mEmalil = emalil;
    }

    public Date getEnrollDate() {
        return mEnrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        mEnrollDate = enrollDate;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

    @Override
    public String toString() {
        return "ReaderBean{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mReaderType=" + mReaderType +
                ", mCompany='" + mCompany + '\'' +
                ", mGender='" + mGender + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mPhoneNum='" + mPhoneNum + '\'' +
                ", mEmalil='" + mEmalil + '\'' +
                ", mEnrollDate=" + mEnrollDate +
                ", mRemark='" + mRemark + '\'' +
                '}';
    }
}
