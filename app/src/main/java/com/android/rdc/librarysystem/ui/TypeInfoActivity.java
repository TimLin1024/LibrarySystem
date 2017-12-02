package com.android.rdc.librarysystem.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.adapter.ViewPagerAdapter;
import com.android.rdc.librarysystem.ui.fragment.BookTypeFragment;
import com.android.rdc.librarysystem.ui.fragment.ReaderTypeFragment;

import butterknife.BindView;

/**
 * 读者类型，图书类型
 */
public class TypeInfoActivity extends BaseToolbarActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_type_info;
    }

    @Override
    protected void initData() {

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
    }

    @Override
    protected void initListener() {

    }

}
