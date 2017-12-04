package com.android.rdc.librarysystem.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book extends DataSupport {
    private int id;//编号
    @Column(nullable = false)
    private String bookName;//书名
    @Column(nullable = false)
    private BookType bookType;//图书类型
    @Column(nullable = false)
    private String authorName;//作者名字
    @Column(nullable = false)
    private String pressName;//出版社名称
    @Column(nullable = false)
    private Date publishDate;//出版日期
    private int price;//价格
    private int pages;//页数
    private String keyWord;//关键词
    @Column(nullable = false)
    private Date enrollDate;//登记日期
    private boolean isBorrowed;//是否借出
    private String remark;//备注
    private List<Borrow> borrowList = new ArrayList<>();//同一本书可以对应多条借阅记录，因为可能有一些书是已经还了


    public int getId() {
        return id;
    }

    public Book setId(int id) {
        this.id = id;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public BookType getBookType() {
        return bookType;
    }

    public Book setBookType(BookType bookType) {
        this.bookType = bookType;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Book setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getPressName() {
        return pressName;
    }

    public Book setPressName(String pressName) {
        this.pressName = pressName;
        return this;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public Book setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Book setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getPages() {
        return pages;
    }

    public Book setPages(int pages) {
        this.pages = pages;
        return this;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public Book setKeyWord(String keyWord) {
        this.keyWord = keyWord;
        return this;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public Book setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
        return this;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public Book setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Book setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookType=" + bookType +
                ", authorName='" + authorName + '\'' +
                ", pressName='" + pressName + '\'' +
                ", publishDate=" + publishDate +
                ", price=" + price +
                ", pages=" + pages +
                ", keyWord='" + keyWord + '\'' +
                ", enrollDate=" + enrollDate +
                ", isBorrowed=" + isBorrowed +
                ", remark='" + remark + '\'' +
                ", borrowList=" + borrowList +
                '}';
    }
}
