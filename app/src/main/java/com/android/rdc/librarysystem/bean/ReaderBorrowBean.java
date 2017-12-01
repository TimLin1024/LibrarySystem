package com.android.rdc.librarysystem.bean;

/**
 * 读者借阅信息
 */
public class ReaderBorrowBean {
    long bookId;
    String bookName;
    long borrowDateTime;
    long returnDateTime;

    public ReaderBorrowBean(long bookId, String bookName, long borrowDateTime, long returnDateTime) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.borrowDateTime = borrowDateTime;
        this.returnDateTime = returnDateTime;
    }

    public ReaderBorrowBean() {
    }

    public long getBookId() {
        return bookId;
    }

    public ReaderBorrowBean setBookId(long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public ReaderBorrowBean setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public long getBorrowDateTime() {
        return borrowDateTime;
    }

    public ReaderBorrowBean setBorrowDateTime(long borrowDateTime) {
        this.borrowDateTime = borrowDateTime;
        return this;
    }

    public long getReturnDateTime() {
        return returnDateTime;
    }

    public ReaderBorrowBean setReturnDateTime(long returnDateTime) {
        this.returnDateTime = returnDateTime;
        return this;
    }

    @Override
    public String toString() {
        return "ReaderBorrowBean{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", borrowDateTime=" + borrowDateTime +
                ", returnDateTime=" + returnDateTime +
                '}';
    }
}
