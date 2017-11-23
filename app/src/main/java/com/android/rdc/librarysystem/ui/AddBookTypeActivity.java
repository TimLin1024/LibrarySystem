package com.android.rdc.librarysystem.ui;

import android.widget.Button;
import android.widget.EditText;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.base.BaseAddActivity;
import com.android.rdc.librarysystem.bean.BookType;

import butterknife.BindView;
import butterknife.OnClick;

public class AddBookTypeActivity extends BaseAddActivity {

    @BindView(R.id.et_book_category_name)
    EditText mEtBookCategoryName;
    @BindView(R.id.et_keyword)
    EditText mEtKeyword;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.btn_add_book_type)
    Button mBtnAddBookType;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_add_book_type;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTitle(R.string.add_book_type);
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.btn_add_book_type)
    public void onViewClicked() {
        BookType bookType = new BookType();
        bookType.setTypeName(getString(mEtBookCategoryName));
        bookType.setKeyWord(getString(mEtKeyword));
        bookType.setRemark(getString(mEtRemark));
        resolveSave(bookType);
    }
}
