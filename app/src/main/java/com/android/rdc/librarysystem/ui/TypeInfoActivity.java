package com.android.rdc.librarysystem.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.adapter.ViewPagerAdapter;
import com.android.rdc.librarysystem.bean.ClickType;
import com.android.rdc.librarysystem.bean.ShowSelectAll;
import com.android.rdc.librarysystem.ui.fragment.BookTypeFragment;
import com.android.rdc.librarysystem.ui.fragment.ReaderTypeFragment;
import com.android.rdc.librarysystem.ui.widget.ScrollControlViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 读者类型，图书类型
 */
public class TypeInfoActivity extends BaseToolbarActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ScrollControlViewPager mViewPager;
    @BindView(R.id.tv_select_all)
    TextView mTvSelectAll;
    @BindView(R.id.include_tv_center_title)
    TextView mIncludeTvCenterTitle;
    @BindView(R.id.include_toolbar)
    Toolbar mIncludeToolbar;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    private boolean mSelectAll = true;//第一次显示为「全选」

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_type_info;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        setTitle("类型信息");
        mTabLayout.setupWithViewPager(mViewPager, true);
        Fragment[] fragments = new Fragment[2];
        fragments[0] = BookTypeFragment.newInstance();
        fragments[1] = ReaderTypeFragment.newInstance();
        String[] tabTitles = new String[]{"书籍类型", "读者类型"};
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), tabTitles, fragments));
        showTabDivider();
    }

    @Override
    protected void initListener() {
        mFloatingActionButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setItems(new String[]{"添加书籍类型", "添加读者类型"}, (dialog, which) -> {
                        switch (which) {
                            case 0:
                                startActivity(AddBookTypeActivity.class);
                                break;
                            case 1:
                                startActivity(AddReaderTypeActivity.class);
                                break;

                        }
                    }).show();
        });
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @OnClick({R.id.tv_select_all, R.id.tv_cancel})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.tv_select_all:
                onSelectAllClick();
                break;
            case R.id.tv_cancel:
                onCancelClickOrBackPressed();
                break;
        }
    }
    /**
     * 选中时可能显示的文字是「全选」或者「全不选」，在这两个状态之间进行转换
     * */
    private void onSelectAllClick() {
        EventBus.getDefault().post(mSelectAll ? ClickType.SELECT_ALL : ClickType.UN_SELECT_ALL);
        mSelectAll = !mSelectAll;//下一次显示与上一次不同
        mTvSelectAll.setText(mSelectAll ? "全选" : "全不选");
    }

    private void onCancelClickOrBackPressed() {
        EventBus.getDefault().post(ClickType.CANCEL);//发送消息，提示点击了「取消」按钮
        mTvSelectAll.setVisibility(View.GONE);
        mTvCancel.setVisibility(View.GONE);
        mViewPager.setScrollable(true);
        showActionBar(true);
        setTabItemClickable(true);
        mFloatingActionButton.setVisibility(View.VISIBLE);
    }

    private void showActionBar(boolean show) {
        getSupportActionBar().setDisplayShowHomeEnabled(show);
        getSupportActionBar().setDisplayHomeAsUpEnabled(show);
        getSupportActionBar().setTitle(show ? "类型信息" : "");
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    public void showButton(ShowSelectAll showSelectAll) {
        mTvSelectAll.setVisibility(View.VISIBLE);
        mTvCancel.setVisibility(View.VISIBLE);
        showActionBar(false);
        mViewPager.setScrollable(false);
        setTabItemClickable(false);
        mFloatingActionButton.setVisibility(View.GONE);//隐藏浮动按钮
    }

    private void setTabItemClickable(boolean clickable) {
        LinearLayout tabStrip = (LinearLayout) mTabLayout.getChildAt(0);
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            View tabView = tabStrip.getChildAt(i);
            if (tabView != null) {
                tabView.setClickable(clickable);
            }
        }
    }

    private void showTabDivider() {
        LinearLayout tabStrip = (LinearLayout) mTabLayout.getChildAt(0);
        tabStrip.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.divider_tab_vertical));
        tabStrip.setDividerPadding(40);
        tabStrip.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
    }

    @Override
    public void onBackPressed() {
        if (mTvCancel.getVisibility() == View.VISIBLE){//如果是现在显示批量删除界面，则隐藏批量删除界面
            onCancelClickOrBackPressed();
            return;
        }
        super.onBackPressed();
    }
}
