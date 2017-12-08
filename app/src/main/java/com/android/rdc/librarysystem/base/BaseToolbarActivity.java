package com.android.rdc.librarysystem.base;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;


public abstract class BaseToolbarActivity extends BaseActivity {

    private static final long HEADER_HIDE_ANIM_DURATION = 1000;//Toolbar 消失/显示的动画时长
    private Toolbar mToolbar;
    protected boolean mIsToolbarShow;
    protected Drawable mToolBarBg;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initToolbar();
    }

    private void initToolbar() {
        mToolbar = $(com.android.rdc.amdroidutil.R.id.include_toolbar);
        if (mToolbar == null) {
            throw new IllegalStateException("No Toolbar");
        }
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        mToolBarBg = new ColorDrawable(getResources().getColor(com.android.rdc.amdroidutil.R.color.colorPrimary));
        getSupportActionBar().setBackgroundDrawable(mToolBarBg);
    }

    @Override
    public void setTitle(CharSequence title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void setTitle(int resId) {
        mToolbar.setTitle(resId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 控制 ToolBar 是否显示,滚动、渐变消失
     */
    protected void showToolbar(boolean show) {
        if (show == mIsToolbarShow || mToolbar == null) {
            return;
        }
        mIsToolbarShow = show;
        if (show) {
            mToolbar.animate()
                    .translationY(0)
                    .alpha(1)
                    .setDuration(HEADER_HIDE_ANIM_DURATION)
                    .setInterpolator(new DecelerateInterpolator());
        } else {
            mToolbar.animate()
                    .translationY(-mToolbar.getBottom())
                    .alpha(0)
                    .setDuration(HEADER_HIDE_ANIM_DURATION)
                    .setInterpolator(new DecelerateInterpolator());
        }
    }

    /**
     * 设置Toolbar 的透明度
     *
     * @param alpha 透明度，取值范围为 0~1，映射到 0~255
     */
    protected void setToolBarAlpha(float alpha) {
        mToolBarBg.setAlpha((int) (alpha * 255));
    }

}
