package com.android.rdc.librarysystem.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 读者类型
 */
public class BookType extends DataSupport {

    private int id;
    @Column(unique = true,nullable = false)
    private String typeName;//名字
    private String keyWord;//关键词
    private String remark;//备注
    private List<Book> bookList = new ArrayList<>();//同一种类型可对应多不同的本书
    boolean isSelected;//该字段不需要映射，故将访问权限设置为 default

    public BookType() {
    }


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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
