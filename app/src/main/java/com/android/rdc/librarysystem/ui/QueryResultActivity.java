package com.android.rdc.librarysystem.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.adapter.BookRvAdapter;
import com.android.rdc.librarysystem.bean.Book;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;

public class QueryResultActivity extends BaseToolbarActivity {
    @BindView(R.id.include_toolbar)
    Toolbar mIncludeToolbar;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.ll_no_match_result)
    LinearLayout mLlNoMatchResult;

    private static final String KEY_QUERY_TEXT = "QUERY_TEXT";
    private BookRvAdapter mRvAdapter;
    private String mSubTitle;

    public static Intent newIntent(Context context, String query) {
        Intent intent = new Intent(context, QueryResultActivity.class);
        intent.putExtra(KEY_QUERY_TEXT, query);
        return intent;
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_query_result;
    }

    @Override
    protected void initData() {
        String query = getIntent().getStringExtra(KEY_QUERY_TEXT);
        resolveQuery(query);
    }

    private void resolveQuery(String query) {
        String condition = "%" + query + "%";
        mSubTitle = query;
        //模糊查询书籍
        List<Book> bookList = DataSupport
                .where("bookname like ? or authorname like ? or pressname like ?", condition, condition, condition)
                .find(Book.class);
        if (bookList.isEmpty()) {
            mRv.setVisibility(View.GONE);
            mLlNoMatchResult.setVisibility(View.VISIBLE);
        } else {
            mSubTitle += "(" + bookList.size() + ")";
            mRvAdapter = new BookRvAdapter();
            mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            mRv.setLayoutManager(new LinearLayoutManager(this));
            mRv.setAdapter(mRvAdapter);
            mRvAdapter.updateData(bookList);
        }
    }

    @Override
    protected void initView() {
        setTitle("图书检索");
        mIncludeToolbar.setSubtitle(mSubTitle);
    }

    @Override
    protected void initListener() {

    }
}
