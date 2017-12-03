package com.android.rdc.librarysystem.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.android.rdc.amdroidutil.base.BaseSimpleRecyclerViewAdapter;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.bean.ReaderType;

import butterknife.BindView;

public class ReaderTypeAdapter extends BaseSimpleRecyclerViewAdapter<ReaderType> {

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

    class StringHolder extends BaseRvHolder implements CompoundButton.OnCheckedChangeListener {

        @BindView(R.id.tv_text)
        TextView mTvText;
        @BindView(R.id.cb_select)
        CheckBox mCb;

        private ReaderType mReaderType;

        public StringHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
        }

        @Override
        protected void bindView(ReaderType readerType) {
            mReaderType = readerType;
            mTvText.setText(readerType.getTypeName());
            if (mShowCheckBox) {
                mCb.setVisibility(View.VISIBLE);
                mCb.setChecked(readerType.isSelected());
            } else {
                mCb.setVisibility(View.GONE);
            }
            mCb.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mReaderType.setSelected(isChecked);
        }
    }
}