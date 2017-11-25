package com.android.rdc.librarysystem;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.amdroidutil.listener.OnClickRecyclerViewListener;
import com.android.rdc.librarysystem.adapter.GridRvAdapter;
import com.android.rdc.librarysystem.model.HomeModel;
import com.android.rdc.librarysystem.ui.AddBookActivity;
import com.android.rdc.librarysystem.ui.AddBookTypeActivity;
import com.android.rdc.librarysystem.ui.AddReaderActivity;
import com.android.rdc.librarysystem.ui.AddReaderTypeActivity;
import com.android.rdc.librarysystem.ui.BorrowBookActivity;
import com.android.rdc.librarysystem.ui.ReturnBookActivity;

import butterknife.BindView;

public class MainActivity extends BaseToolbarActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;
    private GridRvAdapter mAdapter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        setTitle("图书管理系统");
    }

    @Override
    protected void initView() {
        mAdapter = new GridRvAdapter();
        mAdapter.appendData(HomeModel.generateHomeItem());
        mRv.setAdapter(mAdapter);
        mRv.setLayoutManager(new GridLayoutManager(this, 3));
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initListener() {
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            //读者 读者类型 书籍 书籍类型
            @Override
            public void onItemClick(int i) {
                switch (i) {
                    case 0:
                        startActivity(AddReaderActivity.class);
                        break;
                    case 1:
                        startActivity(AddReaderTypeActivity.class);
                        break;
                    case 2:
                        startActivity(AddBookActivity.class);
                        break;
                    case 3:
                        startActivity(AddBookTypeActivity.class);
                        break;
                    case 4:
                        startActivity(BorrowBookActivity.class);
                        break;
                    case 5:
                        startActivity(ReturnBookActivity.class);
                        break;
                    case 6:

                        break;
                    case 7:

                        break;

                    default:
                }
            }

            @Override
            public boolean onItemLongClick(int i) {
                return false;
            }
        });
    }
}
