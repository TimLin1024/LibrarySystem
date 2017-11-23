package com.android.rdc.librarysystem.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.rdc.amdroidutil.base.BaseSimpleRecyclerViewAdapter;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.bean.HomeItemBean;

import butterknife.BindView;

public class GridRvAdapter extends BaseSimpleRecyclerViewAdapter<HomeItemBean> {

    @Override
    protected int setResId() {
        return R.layout.item_home;
    }

    @Override
    protected BaseRvHolder createConcreteViewHolder(View view) {
        return new GridHolder(view);
    }

    protected class GridHolder extends BaseRvHolder {
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_name)
        TextView mTvName;

        public GridHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(HomeItemBean homeItemBean) {
            mIvIcon.setImageResource(homeItemBean.getIconId());
            mTvName.setText(homeItemBean.getItemName());
        }
    }
}
