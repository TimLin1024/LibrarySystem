package com.android.rdc.librarysystem.model;

import android.util.Log;

import com.android.rdc.librarysystem.bean.Book;
import com.android.rdc.librarysystem.bean.BookType;
import com.android.rdc.librarysystem.bean.Reader;
import com.android.rdc.librarysystem.bean.ReaderType;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 默认数据集
 */
public class DefaultDataModel {

    private static final String TAG = "DefaultDataModel";

    /**
     * 默认的书籍类型
     */
    public static List<BookType> generateDefaultBookType() {
        List<BookType> bookTypeList = new ArrayList<>();
        BookType bookType = new BookType();
        bookType.setTypeName("计算机");
        bookTypeList.add(bookType);

        BookType literalType = new BookType();
        literalType.setTypeName("文学");
        bookTypeList.add(literalType);

        BookType artType = new BookType();
        artType.setTypeName("艺术");
        bookTypeList.add(artType);
        return bookTypeList;
    }

    public static List<ReaderType> generateDefaultReaderTypeList() {
        List<ReaderType> readerTypeList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 1, 1);
        ReaderType readerType = new ReaderType();
        readerType.setTypeName("本科生");
        readerType.setExpDate(calendar.getTime());
        readerType.setBorrowMon(1);
        readerType.setBorrowCount(4);
        readerTypeList.add(readerType);

        ReaderType postGraduateReaderType = new ReaderType();
        postGraduateReaderType.setTypeName("研究生");
        postGraduateReaderType.setExpDate(calendar.getTime());
        postGraduateReaderType.setBorrowMon(2);
        postGraduateReaderType.setBorrowCount(8);
        readerTypeList.add(postGraduateReaderType);

        ReaderType teacherType = new ReaderType();
        teacherType.setTypeName("教师");
        teacherType.setExpDate(calendar.getTime());
        teacherType.setBorrowMon(2);
        teacherType.setBorrowCount(10);
        readerTypeList.add(teacherType);

        ReaderType otherType = new ReaderType();
        otherType.setTypeName("其他");
        otherType.setExpDate(calendar.getTime());
        otherType.setBorrowMon(1);
        otherType.setBorrowCount(2);
        readerTypeList.add(otherType);
        return readerTypeList;
    }

    public static List<Reader> generateDefaultReaderList() {
        //注意这里先获取读者类型列表，所以需要先插入读者类型，再获取默认读者
        List<ReaderType> readerTypeList = DataSupport.findAll(ReaderType.class);
        if (readerTypeList.isEmpty()) {
            Log.e(TAG, "generateDefaultReaderList: ReaderType is null");
            return Collections.emptyList();
        }
        Random random = new Random();
        List<Reader> readerList = new ArrayList<>();
        Reader reader1 = new Reader()
                .setName("刘鹏")
                .setGender("男")
                .setEnrollDate(new Date())
                .setPhoneNum("15521119812")
                .setEmail("652361@gmail.com")
                .setCompany("艾利有限公司")
                .setReaderType(readerTypeList.get(random.nextInt(readerTypeList.size())));
        readerList.add(reader1);

        Reader reader2 = new Reader()
                .setName("李文")
                .setGender("男")
                .setEnrollDate(new Date())
                .setPhoneNum("15521129812")
                .setEmail("327312@gmail.com")
                .setCompany("博文有限公司")
                .setReaderType(readerTypeList.get(random.nextInt(readerTypeList.size())));
        readerList.add(reader2);

        Reader reader3 = new Reader()
                .setName("徐燕")
                .setGender("女")
                .setEnrollDate(new Date())
                .setPhoneNum("15524129812")
                .setEmail("327312@gmail.com")
                .setCompany("广工")
                .setReaderType(readerTypeList.get(random.nextInt(readerTypeList.size())));
        readerList.add(reader3);
        return readerList;
    }

    /**
     * 生成默认的图书列表
     */
    public static List<Book> generateDefaultBookList() {
        List<BookType> bookTypeList = DataSupport.findAll(BookType.class);
        //注意这里先获取书籍类型列表，所以需要先插入书籍类型，再获取默认读者
        if (bookTypeList.isEmpty()) {
            Log.e(TAG, "generateDefaultReaderList: BookTypeList is null");
            return Collections.emptyList();
        }
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        List<Book> bookList = new ArrayList<>();

        calendar.set(2015, 10, 2);
        Book book1 = new Book()
                .setBookName("计算机网络")
                .setAuthorName("谢希仁")
                .setEnrollDate(new Date())
                .setPublishDate(calendar.getTime())
                .setPressName("电子工业出版社")
                .setBookType(bookTypeList.get(random.nextInt(bookTypeList.size())));
        bookList.add(book1);

        calendar.set(2011, 3, 15);
        Book book2 = new Book()
                .setBookName("深入理解Android")
                .setAuthorName("邓凡平")
                .setPublishDate(calendar.getTime())
                .setEnrollDate(new Date())
                .setPressName("机械工业出版社")
                .setBookType(bookTypeList.get(random.nextInt(bookTypeList.size())));
        bookList.add(book2);

        calendar.set(2010, 3, 15);
        Book book3 = new Book()
                .setBookName("挪威的森林")
                .setAuthorName("村上村树")
                .setPublishDate(calendar.getTime())
                .setEnrollDate(new Date())
                .setPressName("文艺出版社")
                .setBookType(bookTypeList.get(random.nextInt(bookTypeList.size())));
        bookList.add(book3);

        calendar.set(2012, 3, 15);
        Book book4 = new Book()
                .setBookName("大学之路")
                .setAuthorName("吴军")
                .setPublishDate(calendar.getTime())
                .setEnrollDate(new Date())
                .setPressName("人民邮电出版社")
                .setBookType(bookTypeList.get(random.nextInt(bookTypeList.size())));
        bookList.add(book4);
        return bookList;
    }

}
