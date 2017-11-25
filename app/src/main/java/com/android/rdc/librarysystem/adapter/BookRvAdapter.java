package com.android.rdc.librarysystem.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.android.rdc.amdroidutil.base.BaseSimpleRecyclerViewAdapter;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.bean.Book;
import com.android.rdc.librarysystem.util.DateUtil;

import butterknife.BindView;

public class BookRvAdapter extends BaseSimpleRecyclerViewAdapter<Book> {
    @Override
    protected int setResId() {
        return R.layout.item_book;
    }

    @Override
    protected BaseRvHolder createConcreteViewHolder(View view) {
        return new BookHolder(view);
    }

    class BookHolder extends BaseRvHolder {

        @BindView(R.id.tv_book_name)
        TextView mTvBookName;
        @BindView(R.id.tv_author)
        TextView mTvAuthor;
        @BindView(R.id.tv_publish_date_and_press)
        TextView mTvPublishDateAndPress;
        @BindView(R.id.tv_is_in_library)
        TextView mTvIsInLibrary;

        public BookHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bindView(Book book) {
            mTvBookName.setText(book.getBookName());
            mTvAuthor.setText(String.format("作者：%s", book.getAuthorName()));
            mTvPublishDateAndPress.setText(String.format("出版：%s/%s", DateUtil.dayFormat(book.getPublishDate()), book.getPressName()));
            if (book.isBorrowed()) {
                mTvIsInLibrary.setText("在馆：否");
                mTvIsInLibrary.setTextColor(Color.RED);
            } else {
                mTvIsInLibrary.setText("在馆：是");
                mTvIsInLibrary.setTextColor(Color.parseColor("#ADD8E6"));
            }
        }
    }
}
