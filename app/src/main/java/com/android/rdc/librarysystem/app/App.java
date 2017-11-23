package com.android.rdc.librarysystem.app;

import com.facebook.stetho.Stetho;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

public class App extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        Connector.getDatabase();//初始化数据库
    }
}
