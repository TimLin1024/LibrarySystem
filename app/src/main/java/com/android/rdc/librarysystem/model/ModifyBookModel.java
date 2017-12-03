package com.android.rdc.librarysystem.model;

import android.content.ContentValues;

import com.android.rdc.librarysystem.bean.Book;
import com.android.rdc.librarysystem.contract.ModifyBookContract;

import org.litepal.crud.DataSupport;

public class ModifyBookModel implements ModifyBookContract.Model {

    @Override
    public void getBookData(long id, OnBookGetListener listener) {
        Book book = DataSupport.find(Book.class, id);
        listener.onBookGet(book);
    }

    @Override
    public void deleteBook(long id, OnBookDeleteListener listener) {
        int rowAffect = DataSupport.delete(Book.class, id);//级联删除
        listener.onBookDelete(rowAffect);
    }

    @Override
    public void modifyBook(Book book, OnBookModifyListener listener) {
        //litepal 的 DataSupport 子类对象 update 无法更新外键，也就是更新的那张表的外键丢失;
        // 试用 save, save 会导致关联表的外键丢失 --> 不可行；
        // 应该使用 Sqlite 的 update
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookname", book.getBookName());
        contentValues.put("authorname", book.getAuthorName());
        contentValues.put("pressName", book.getPressName());
        contentValues.put("publishdate", book.getPublishDate().getTime());
        contentValues.put("enrolldate", book.getEnrollDate().getTime());
        contentValues.put("pages", book.getPages());
        contentValues.put("price", book.getPrice());
        contentValues.put("keyword", book.getKeyWord());
        contentValues.put("remark", book.getRemark());
//        contentValues.put("isborrow",book.isBorrowed());
        contentValues.put("booktype_id", book.getBookType().getId());
        int rowAffect = DataSupport.update(Book.class, contentValues, book.getId());

        listener.onBookModify(rowAffect);
    }
}
