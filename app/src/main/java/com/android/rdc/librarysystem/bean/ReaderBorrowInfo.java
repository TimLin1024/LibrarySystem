package com.android.rdc.librarysystem.bean;

/**
 * 读者借阅信息
 */
public class ReaderBorrowInfo {
    private long bookId;
    private String bookMsg;
    private long borrowDateTime;
    private long returnDateTime;

    public ReaderBorrowInfo(long bookId, String bookMsg, long borrowDateTime, long returnDateTime) {
        this.bookId = bookId;
        this.bookMsg = bookMsg;
        this.borrowDateTime = borrowDateTime;
        this.returnDateTime = returnDateTime;
    }

    public ReaderBorrowInfo() {
    }

    public long getBookId() {
        return bookId;
    }

    public ReaderBorrowInfo setBookId(long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookMsg() {
        return bookMsg;
    }

    public ReaderBorrowInfo setBookMsg(String bookMsg) {
        this.bookMsg = bookMsg;
        return this;
    }

    public long getBorrowDateTime() {
        return borrowDateTime;
    }

    public ReaderBorrowInfo setBorrowDateTime(long borrowDateTime) {
        this.borrowDateTime = borrowDateTime;
        return this;
    }

    public long getReturnDateTime() {
        return returnDateTime;
    }

    public ReaderBorrowInfo setReturnDateTime(long returnDateTime) {
        this.returnDateTime = returnDateTime;
        return this;
    }

    @Override
    public String toString() {
        return "ReaderBorrowInfo{" +
                "bookId=" + bookId +
                ", bookMsg='" + bookMsg + '\'' +
                ", borrowDateTime=" + borrowDateTime +
                ", returnDateTime=" + returnDateTime +
                '}';
    }
}
