package com.android.rdc.librarysystem;

import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.amdroidutil.listener.OnClickRecyclerViewListener;
import com.android.rdc.librarysystem.adapter.GridRvAdapter;
import com.android.rdc.librarysystem.model.HomeModel;
import com.android.rdc.librarysystem.ui.AddBookActivity;
import com.android.rdc.librarysystem.ui.AddBookTypeActivity;
import com.android.rdc.librarysystem.ui.AddReaderActivity;
import com.android.rdc.librarysystem.ui.AddReaderTypeActivity;
import com.android.rdc.librarysystem.ui.BorrowBookActivity;
import com.android.rdc.librarysystem.ui.QueryBookActivity;
import com.android.rdc.librarysystem.ui.QueryReaderActivity;
import com.android.rdc.librarysystem.ui.ReturnBookActivity;
import com.android.rdc.librarysystem.ui.StatisticsAnalysisActivity;
import com.android.rdc.librarysystem.ui.TypeInfoActivity;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseToolbarActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.boom_menu_btn)
    BoomMenuButton mBoomMenuButton;
    @BindView(R.id.collapsing_tb_layout)
    CollapsingToolbarLayout mCollapsingTbLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    private GridRvAdapter mAdapter;

    private Drawable[] mDrawables;
    private String[] mBtnText = new String[]{"添加读者", "添加读者类型", "添加书籍", "添加书籍类型"};
    private int[][] mSubBtnColorList = {
            {
                    R.color.white, R.color.colorPrimaryDark
            },
            {
                    R.color.wheat, R.color.colorPrimaryDark
            },
            {
                    R.color.colorPrimary, R.color.colorPrimaryDark
            },
            {
                    R.color.colorPrimary, R.color.yellow
            }
    };


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
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (mDrawables == null) {
            mDrawables = new Drawable[]{getResources().getDrawable(R.drawable.ic_add),
                    getResources().getDrawable(R.drawable.ic_add_reader),
                    getResources().getDrawable(R.drawable.ic_add),
                    getResources().getDrawable(R.drawable.ic_book)};
        }
        mBoomMenuButton.init(
                mDrawables,
                mBtnText,
                mSubBtnColorList,
                ButtonType.HAM,
                BoomType.LINE,
                PlaceType.HAM_4_1,
                null,
                null,
                null,
                null,
                null,
                null,
                0
        );
        mBoomMenuButton.setOnSubButtonClickListener(buttonIndex -> {
            switch (buttonIndex) {
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
            }
        });
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
                        startActivity(QueryBookActivity.class);
                        break;
                    case 7:
                        startActivity(QueryReaderActivity.class);
                        break;
                    case 8:
                        startActivity(StatisticsAnalysisActivity.class);
                        break;
                    case 9:
                        startActivity(TypeInfoActivity.class);
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

    @OnClick({R.id.collapsing_tb_layout, R.id.app_bar_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collapsing_tb_layout:
                break;
            case R.id.app_bar_layout:
                break;
        }
    }
}
