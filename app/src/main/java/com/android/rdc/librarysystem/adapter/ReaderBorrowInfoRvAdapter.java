package com.android.rdc.librarysystem.adapter;

import android.view.View;
import android.widget.TextView;

import com.android.rdc.amdroidutil.base.BaseSimpleRecyclerViewAdapter;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.bean.ReaderBorrowInfo;
import com.android.rdc.librarysystem.util.DateUtil;

import java.util.Date;

import butterknife.BindView;

public class ReaderBorrowInfoRvAdapter extends BaseSimpleRecyclerViewAdapter<ReaderBorrowInfo> {

    @Override
    protected int setResId() {
        return R.layout.item_reader_borrow_info;
    }

    @Override
    protected BaseRvHolder createConcreteViewHolder(View view) {
        return new ReaderBorrowInfoHolder(view);
    }

    class ReaderBorrowInfoHolder extends BaseRvHolder {

        @BindView(R.id.tv_book_msg)
        TextView mTvBookMsg;
        @BindView(R.id.tv_borrow_date)
        TextView mTvBorrowDate;
        @BindView(R.id.tv_return_date)
        TextView mTvReturnDate;
        @BindView(R.id.tv_has_return)
        TextView mTvHasReturn;

        public ReaderBorrowInfoHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(ReaderBorrowInfo readerBorrowInfo) {
            mTvBookMsg.setText(readerBorrowInfo.getBookMsg());
            mTvBorrowDate.setText(String.format("借期：%s", DateUtil.dayFormat(new Date(readerBorrowInfo.getBorrowDateTime()))));
            String returnDate = "还期：";
            if (readerBorrowInfo.getReturnDateTime() != 0) {
                returnDate += DateUtil.dayFormat(new Date(readerBorrowInfo.getReturnDateTime()));
                mTvHasReturn.setText("已还");
            } else {
                mTvHasReturn.setText("未还");
            }
            mTvReturnDate.setText(returnDate);
        }
    }
}
