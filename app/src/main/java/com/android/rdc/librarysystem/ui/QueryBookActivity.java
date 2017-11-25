package com.android.rdc.librarysystem.ui;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.adapter.BookRvAdapter;
import com.android.rdc.librarysystem.bean.Book;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;

public class QueryBookActivity extends BaseToolbarActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;
    private BookRvAdapter mAdapter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_query_book;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTitle("书籍查询");
        mAdapter = new BookRvAdapter();
        List<Book> bookList = DataSupport.findAll(Book.class);
        mAdapter.updateData(bookList);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {

    }

}
