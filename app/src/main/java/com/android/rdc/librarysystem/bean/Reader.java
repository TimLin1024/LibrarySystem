package com.android.rdc.librarysystem.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 读者类型
 */
public class Reader extends DataSupport {

    private int id;//编号
    private String name;//读者姓名
    private ReaderType readerType;//读者类型
    private String company;//工作单位
    private String gender;//性别
    private String address;//家庭地址
    private String phoneNum;//电话号码
    private String email;//邮箱
    private Date enrollDate;//登记日期
    private String remark;//备注
    @Column(defaultValue = "0")
    private int currentBorrowCount;//当前借阅的书籍数目
    //    private List<Book> mBookList = new ArrayList<>();
    private List<Borrow> borrowList = new ArrayList<>();//同一读者可以对应多条借阅记录，可以借阅多种

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReaderType getReaderType() {
        return readerType;
    }

    public void setReaderType(ReaderType readerType) {
        this.readerType = readerType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCurrentBorrowCount() {
        return currentBorrowCount;
    }

    public void setCurrentBorrowCount(int currentBorrowCount) {
        this.currentBorrowCount = currentBorrowCount;
    }

    public void increaseCurrentBorrowCount() {
        this.currentBorrowCount++;
    }

    public void decreaseCurrentBorrowCount() {
        this.currentBorrowCount--;
    }


}
