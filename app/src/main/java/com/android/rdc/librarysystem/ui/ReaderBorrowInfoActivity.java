package com.android.rdc.librarysystem.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.bean.ReaderBorrowBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReaderBorrowInfoActivity extends BaseToolbarActivity {

    private static final String KEY_READER_ID = "READER_ID";
    @BindView(R.id.rv)
    RecyclerView mRv;

    private long mReaderId;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_reader_borrow_info;
    }

    @Override
    protected void initData() {
        mReaderId = getIntent().getLongExtra(KEY_READER_ID, -1);

        getReaderInfoList();

    }

    private List<ReaderBorrowBean> getReaderInfoList() {
        //根据读者 id ，查询所借阅所有的书籍 ,首先去 borrow 表中查询 该 id 借阅的所有书籍的 id，根据 bookId 去 book 表中查询所有对应的 book
        Cursor cursor = DataSupport.findBySQL("select book_id,bookname,borrowdate,returndate from borrow,book where book.id = borrow.book_id and reader_id = " + mReaderId);
        List<ReaderBorrowBean> readerBorrowBeanList = new ArrayList<>();
        //return date 为 null 证明未还，不为null 证明已经还了
        if (cursor != null && cursor.moveToFirst()) {
            readerBorrowBeanList.add(getReaderBorrowBeanFromCursor(cursor));
            while (cursor.moveToNext()) {
                readerBorrowBeanList.add(getReaderBorrowBeanFromCursor(cursor));
            }
        }
        return readerBorrowBeanList;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    public static void startActivity(Context context, long readerId) {
        Intent intent = new Intent(context, ReaderBorrowInfoActivity.class);
        intent.putExtra(KEY_READER_ID, readerId);
        context.startActivity(intent);
    }

    private ReaderBorrowBean getReaderBorrowBeanFromCursor(Cursor cursor) {
        return new ReaderBorrowBean()
                .setBookId(cursor.getLong(0))
                .setBookName(cursor.getString(1))
                .setBorrowDateTime(cursor.getLong(2))
                .setReturnDateTime(cursor.getLong(3));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
