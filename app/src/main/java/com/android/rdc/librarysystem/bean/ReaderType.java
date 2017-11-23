package com.android.rdc.librarysystem.bean;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * 读者类型
 */
public class ReaderType extends DataSupport {
    private int id;
    private String typeName;//本科生 研究生 博士生 教师
    private int borrowCount;
    private int borrowMon;//借书的期限
    private Date expDate;//过期期限，也就是有效期
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

    public int getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }

    public int getBorrowMon() {
        return borrowMon;
    }

    public void setBorrowMon(int borrowMon) {
        this.borrowMon = borrowMon;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
