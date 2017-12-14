package com.android.rdc.librarysystem;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.amdroidutil.listener.OnClickRecyclerViewListener;
import com.android.rdc.librarysystem.adapter.GridRvAdapter;
import com.android.rdc.librarysystem.model.HomeModel;
import com.android.rdc.librarysystem.ui.AddBookActivity;
import com.android.rdc.librarysystem.ui.AddReaderActivity;
import com.android.rdc.librarysystem.ui.BorrowBookActivity;
import com.android.rdc.librarysystem.ui.QueryBookActivity;
import com.android.rdc.librarysystem.ui.QueryReaderActivity;
import com.android.rdc.librarysystem.ui.ReturnBookActivity;
import com.android.rdc.librarysystem.ui.StatisticsAnalysisActivity;
import com.android.rdc.librarysystem.ui.TypeInfoActivity;

import butterknife.BindView;

public class MainActivity extends BaseToolbarActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.collapsing_tb_layout)
    CollapsingToolbarLayout mCollapsingTbLayout;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    private GridRvAdapter mAdapter;

    @BindView(R.id.iv)
    ImageView mImageView;
    private long mLastPressedTime;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        //noinspection ConstantConditions
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

        initCollapsingTbLayout();
    }

    private void initCollapsingTbLayout() {
        mCollapsingTbLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent));//透明
        mCollapsingTbLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
    }


    @Override
    protected void initListener() {
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            //读者 读者类型 书籍 书籍类型
            @Override
            public void onItemClick(int i) {
                handleRvItemClick(i);
            }

            @Override
            public boolean onItemLongClick(int i) {
                return false;
            }
        });
    }

    private void handleRvItemClick(int i) {
        switch (i) {
            case 0:
                startActivity(AddReaderActivity.class);
                break;
            case 1:
                startActivity(BorrowBookActivity.class);
                break;
            case 2:
                startActivity(AddBookActivity.class);
                break;

            case 3:
                startActivity(QueryReaderActivity.class);
                break;
            case 4:
                startActivity(ReturnBookActivity.class);
                break;
            case 5:
                startActivity(QueryBookActivity.class);
                break;
            case 6:
                startActivity(TypeInfoActivity.class);
                break;
            case 7:
                startActivity(StatisticsAnalysisActivity.class);
                break;
            default:
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastPressedTime <= 2 * 1000) {
            finish();
        } else {
            mLastPressedTime = System.currentTimeMillis();
            showToast("再按一次退出本应用");
        }
    }
}
