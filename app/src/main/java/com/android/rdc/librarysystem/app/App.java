package com.android.rdc.librarysystem.app;

import android.content.Context;

import com.android.rdc.librarysystem.bean.Book;
import com.android.rdc.librarysystem.bean.BookType;
import com.android.rdc.librarysystem.bean.Reader;
import com.android.rdc.librarysystem.bean.ReaderType;
import com.android.rdc.librarysystem.model.DefaultDataModel;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

public class App extends LitePalApplication {
    private static final String TAG = "App";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        boolean isInSamplerProcess = BlockCanaryEx.isInSamplerProcess(this);
//        if (!isInSamplerProcess) {
//            BlockCanaryEx.install(new Config(this));
//        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        Connector.getDatabase();//初始化数据库
        addDefaultData();
        setupLeakCanary();//内存泄漏检测
        CrashReport.initCrashReport(getApplicationContext());//初始化 bugly
    }

        protected RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }


    private void addDefaultData() {
        addDefaultBookType();
        addDefaultReaderType();
        addDefaultBook();
        addDefaultReader();
    }

    private void addDefaultReader() {
        if (DataSupport.findFirst(Reader.class) != null) {
            return;
        }
        DataSupport.saveAll(DefaultDataModel.generateDefaultReaderList());
    }

    private void addDefaultBook() {
        if (DataSupport.findFirst(Book.class) != null) {
            return;
        }
        DataSupport.saveAll(DefaultDataModel.generateDefaultBookList());
    }

    private void addDefaultReaderType() {
        if (DataSupport.findFirst(ReaderType.class) != null) {
            return;
        }
        DataSupport.saveAll(DefaultDataModel.generateDefaultReaderTypeList());
    }

    private void addDefaultBookType() {
        if (DataSupport.findFirst(BookType.class) != null) {
            return;
        }
        DataSupport.saveAll(DefaultDataModel.generateDefaultBookType());
    }
}
