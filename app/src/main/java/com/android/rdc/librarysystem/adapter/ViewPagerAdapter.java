package com.android.rdc.librarysystem.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Fragment[] mFragments;
    private String[] mTabTitles;

    public ViewPagerAdapter(FragmentManager fm, String[] tabTitles,Fragment[] fragments) {
        super(fm);
        if (fragments.length != tabTitles.length) {
            throw new IllegalArgumentException("Fragment 数量与标题数量不同");
        }
        mFragments = fragments;
        mTabTitles = tabTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}
