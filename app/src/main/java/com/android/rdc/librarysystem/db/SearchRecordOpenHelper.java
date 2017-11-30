package com.android.rdc.librarysystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.rdc.librarysystem.constant.Constants;

/**
 * 这里用 SQLLite 实现，不用 Litepal，复习下「基础知识」
 * */
public class SearchRecordOpenHelper extends SQLiteOpenHelper {
    public SearchRecordOpenHelper(Context context) {
        super(context, "record.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table record(" +
                "id integer primary key autoincrement ," +
                Constants.SEARCH_RECORD_COLUMN_NAME + " varchar(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
