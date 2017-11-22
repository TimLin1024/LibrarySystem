package com.android.rdc.librarysystem.bean;

/**
 * 读者类型
 */
public class BookType {

    private Long mId;
    private String mTypeName;//名字
    private String mKeyWord;//关键词
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

    public String getKeyWord() {
        return mKeyWord;
    }

    public void setKeyWord(String keyWord) {
        mKeyWord = keyWord;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "mId=" + mId +
                ", mTypeName='" + mTypeName + '\'' +
                ", mKeyWord='" + mKeyWord + '\'' +
                ", mRemark='" + mRemark + '\'' +
                '}';
    }
}
