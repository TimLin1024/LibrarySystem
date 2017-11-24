package com.android.rdc.librarysystem.bean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book extends DataSupport {
    private int id;//编号
    private String bookName;//书名
    private BookType bookType;//图书类型
    private String authorName;//作者名字
    private String pressName;//出版社名称
    private Date publishDate;//出版日期
    private int price;//价格
    private int pages;//页数
    private String keyWord;//关键词
    private Date enrollDate;//登记日期
    private boolean isBorrowed;//是否借出
    private String remark;//备注
    //    private List<Reader> mReaderList = new ArrayList<>();
    private List<BookType> bookTypeList = new ArrayList<>();//同一本书可以有多种类型，比如 程序开发，移动开发
    private List<Borrow> borrowList = new ArrayList<>();//同一本书可以对应多条借阅记录，因为可能有一些数是已经还了


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPressName() {
        return pressName;
    }

    public void setPressName(String pressName) {
        this.pressName = pressName;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
