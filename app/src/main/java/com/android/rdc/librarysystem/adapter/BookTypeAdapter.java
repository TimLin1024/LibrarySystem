package com.android.rdc.librarysystem.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.rdc.amdroidutil.base.BaseSimpleRecyclerViewAdapter;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.bean.BookType;

import butterknife.BindView;

public class BookTypeAdapter extends BaseSimpleRecyclerViewAdapter<BookType> {

    private boolean mShowCheckBox;//是否显示 CheckBox

    @Override
    protected int setResId() {
        return R.layout.item_text;
    }

    @Override
    protected BaseRvHolder createConcreteViewHolder(View view) {
        return new StringHolder(view);
    }

    public void setShowCheckBox(boolean showCheckBox) {
        mShowCheckBox = showCheckBox;
        notifyDataSetChanged();
    }

    class StringHolder extends BaseRvHolder {

        @BindView(R.id.tv_text)
        TextView mTvText;
        @BindView(R.id.cb_select)
        CheckBox mCb;

        public StringHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(BookType bookType) {
            mTvText.setText(bookType.getTypeName());
            if (mShowCheckBox) {
                mCb.setVisibility(View.VISIBLE);
                mCb.setChecked(bookType.isSelected());
            } else {
                mCb.setVisibility(View.GONE);
            }
        }

    }
}