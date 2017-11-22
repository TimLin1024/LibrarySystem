package com.android.rdc.librarysystem.app;

import com.facebook.stetho.Stetho;

import org.litepal.LitePalApplication;

public class App extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
