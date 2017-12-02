package com.android.rdc.librarysystem.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.rdc.librarysystem.constant.Constants;
import com.android.rdc.librarysystem.db.SearchRecordOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 这里用 SQLLite 实现，不用 Litepal，复习下基础知识
 */
public class RecordDao {
    private volatile static RecordDao sRecordDao;
    private SQLiteOpenHelper mHelper;

    private RecordDao(Context applicationContext) {
        mHelper = new SearchRecordOpenHelper(applicationContext);
    }

    public static RecordDao getInstance(Context context) {
        if (sRecordDao == null) {
            synchronized (RecordDao.class) {
                if (sRecordDao == null) {
                    sRecordDao = new RecordDao(context.getApplicationContext());
                }
            }
        }
        return sRecordDao;
    }

    /**
     * 插入记录
     */
    public void insert(String record) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.SEARCH_RECORD_COLUMN_NAME, record);
        mHelper.getWritableDatabase().insert(Constants.SEARCH_RECORD_TABLE_NAME, null, contentValues);
    }

    /**
     * 删除指定记录
     */
    public void deleteRecord(String record) {
        mHelper.getWritableDatabase().delete(Constants.SEARCH_RECORD_TABLE_NAME, Constants.SEARCH_RECORD_COLUMN_NAME + "=?", new String[]{record});
    }

    /**
     * 删除所有记录
     */
    public void deleteAllRecord() {
        mHelper.getWritableDatabase().delete(Constants.SEARCH_RECORD_TABLE_NAME, null, null);
    }

    public List<String> queryRecord() {
        List<String> recordList = new ArrayList<>();
        Cursor cursor = mHelper.getReadableDatabase().query(Constants.SEARCH_RECORD_TABLE_NAME, null,
                null, null,
                null, null, "id desc");//按照时间先后排序，
        if (cursor != null && cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(Constants.SEARCH_RECORD_COLUMN_NAME);
            recordList.add(cursor.getString(index));
            while (cursor.moveToNext()) {
                recordList.add(cursor.getString(index));
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return recordList;
    }

}
