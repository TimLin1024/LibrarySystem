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
    @Column(nullable = false)
    private String name;//读者姓名
    @Column(nullable = false)
    private ReaderType readerType;//读者类型
    private String company;//工作单位
    private String gender;//性别
    private String address;//家庭地址
    @Column(nullable = false)
    private String phoneNum;//电话号码
    private String email;//邮箱
    @Column(nullable = false)
    private Date enrollDate;//登记日期
    private String remark;//备注
    private int currentBorrowCount;//当前借阅的书籍数目
    //    private List<Book> mBookList = new ArrayList<>();
    private List<Borrow> borrowList = new ArrayList<>();//同一读者可以对应多条借阅记录，可以借阅多种

    public int getId() {
        return id;
    }

    public Reader setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Reader setName(String name) {
        this.name = name;
        return this;
    }

    public ReaderType getReaderType() {
        return readerType;
    }

    public Reader setReaderType(ReaderType readerType) {
        this.readerType = readerType;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public Reader setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Reader setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Reader setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Reader setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Reader setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public Reader setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Reader setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public int getCurrentBorrowCount() {
        return currentBorrowCount;
    }

    public Reader setCurrentBorrowCount(int currentBorrowCount) {
        this.currentBorrowCount = currentBorrowCount;
        return this;
    }

    public void increaseCurrentBorrowCount() {
        this.currentBorrowCount++;
    }

    public void decreaseCurrentBorrowCount() {
        this.currentBorrowCount--;
    }


}
