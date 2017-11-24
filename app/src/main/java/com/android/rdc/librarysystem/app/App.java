package com.android.rdc.librarysystem.app;

import com.android.rdc.librarysystem.bean.BookType;
import com.android.rdc.librarysystem.bean.ReaderType;
import com.facebook.stetho.Stetho;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class App extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        Connector.getDatabase();//初始化数据库
        addDefaultData();
    }

    private void addDefaultData() {
        //当不存在书籍类型时，添加几种默认的类型
        addDefaultBookType();
        addDefaultReaderType();
    }

    private void addDefaultReaderType() {
        if (DataSupport.findFirst(ReaderType.class) != null) {
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 1, 1);
        ReaderType readerType = new ReaderType();
        readerType.setTypeName("本科生");
        readerType.setExpDate(calendar.getTime());
        readerType.setBorrowMon(1);
        readerType.setBorrowCount(4);
        readerType.save();

        ReaderType postGraduateReaderType = new ReaderType();
        postGraduateReaderType.setTypeName("研究生");
        postGraduateReaderType.setExpDate(calendar.getTime());
        postGraduateReaderType.setBorrowMon(2);
        postGraduateReaderType.setBorrowCount(8);
        postGraduateReaderType.save();

        ReaderType teacherType = new ReaderType();
        teacherType.setTypeName("教师");
        teacherType.setExpDate(calendar.getTime());
        teacherType.setBorrowMon(2);
        teacherType.setBorrowCount(10);
        teacherType.save();
    }

    private void addDefaultBookType() {
        if (DataSupport.findFirst(BookType.class) != null) {
            return;
        }
        List<BookType> bookTypeList = new ArrayList<>();
        BookType bookType = new BookType();
        bookType.setTypeName("计算机");
        bookTypeList.add(bookType);

        BookType literalType = new BookType();
        literalType.setTypeName("文学");
        literalType.setKeyWord("小说");
        bookTypeList.add(literalType);

        BookType artType = new BookType();
        artType.setTypeName("艺术");
        bookTypeList.add(artType);

        DataSupport.saveAllAsync(bookTypeList);
    }
}
