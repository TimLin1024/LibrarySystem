package com.android.rdc.librarysystem.adapter;

import android.view.View;
import android.widget.TextView;

import com.android.rdc.amdroidutil.base.BaseSimpleRecyclerViewAdapter;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.bean.Reader;

import java.util.Locale;

import butterknife.BindView;

public class ReaderRvAdapter extends BaseSimpleRecyclerViewAdapter<Reader> {
    @Override
    protected int setResId() {
        return R.layout.item_reader;
    }

    @Override
    protected BaseRvHolder createConcreteViewHolder(View view) {
        return new ReaderHolder(view);
    }

    class ReaderHolder extends BaseRvHolder {

        @BindView(R.id.tv_reader_name)
        TextView mTvReaderName;
        @BindView(R.id.tv_reader_num)
        TextView mTvReaderNum;
        @BindView(R.id.tv_contact_way)
        TextView mTvContactWay;

        public ReaderHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(Reader reader) {
            mTvReaderName.setText(String.format("%s", reader.getName()));
            mTvReaderNum.setText(String.format(Locale.CHINA, "读者编号：%d", reader.getId()));
            mTvContactWay.setText(String.format("联系方式：%s", reader.getPhoneNum()));
        }
    }
}
