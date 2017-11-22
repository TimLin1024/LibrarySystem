package com.android.rdc.librarysystem.bean;

import org.litepal.crud.DataSupport;

/**
 * 读者类型
 */
public class BookType extends DataSupport {

    private int id;
    private String typeName;//名字
    private String keyWord;//关键词
    private String remark;//备注

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
