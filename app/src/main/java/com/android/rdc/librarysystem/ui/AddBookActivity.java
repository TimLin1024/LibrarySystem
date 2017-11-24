package com.android.rdc.librarysystem.ui;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.base.BaseAddActivity;
import com.android.rdc.librarysystem.bean.Book;
import com.android.rdc.librarysystem.bean.BookType;
import com.android.rdc.librarysystem.util.DateUtil;
import com.bigkoo.pickerview.TimePickerView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加书籍
 */
public class AddBookActivity extends BaseAddActivity {

    @BindView(R.id.et_book_name)
    EditText mEtBookName;
    @BindView(R.id.sp_book_type)
    Spinner mSpBookType;
    @BindView(R.id.tv_publish_date)
    TextView mTvPublishDate;
    @BindView(R.id.tv_enroll_date)
    TextView mTvEnrollDate;
    @BindView(R.id.et_author_name)
    EditText mEtAuthorName;
    @BindView(R.id.et_press_name)
    EditText mEtPressName;
    @BindView(R.id.et_price)
    EditText mEtPrice;
    @BindView(R.id.et_page_num)
    EditText mEtPageNum;
    @BindView(R.id.et_keywords)
    EditText mEtKeywords;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.btn_add_book)
    Button mBtnAddBook;

    private Date mPublishDate;
    private Date mEnrollDate;
    private List<BookType> mBookTypeList;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_add_book;
    }

    @Override
    protected void initData() {
        mBookTypeList = DataSupport.findAll(BookType.class);
    }

    @Override
    protected void initView() {
        setTitle(R.string.add_book);
    }

    @Override
    protected void initListener() {
        List<String> stringList = new ArrayList<>();
        for (BookType bookType : mBookTypeList) {
            stringList.add(bookType.getTypeName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, stringList);
        mSpBookType.setAdapter(arrayAdapter);
    }

    @OnClick({R.id.tv_publish_date, R.id.tv_enroll_date, R.id.btn_add_book})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_publish_date:
                showPickerView(mTvPublishDate);
                break;
            case R.id.tv_enroll_date:
                showPickerView(mTvEnrollDate);
                break;
            case R.id.btn_add_book:
                Book book = new Book();
                book.setBookName(getString(mEtBookName));
                book.setBookType(mBookTypeList.get(mSpBookType.getSelectedItemPosition()));
                book.setAuthorName(getString(mEtAuthorName));
                book.setPressName(getString(mEtPressName));
                book.setPublishDate(mPublishDate);
                book.setPrice(getNumberFromEt(mEtPrice, 0));
                book.setPages(getNumberFromEt(mEtPageNum, 0));
                book.setKeyWord(getString(mEtKeywords));
                book.setEnrollDate(mEnrollDate);
                book.setBorrowed(false);//刚登记入库，默认未借出
                book.setRemark(getString(mEtRemark));
                resolveSave(book);
                break;
        }
    }

    private void showPickerView(TextView tv) {
        new TimePickerView.Builder(this, (date, v) -> {
            switch (tv.getId()) {
                case R.id.tv_publish_date:
                    mPublishDate = date;
                    mTvPublishDate.setText(String.format("出版日期：%s", DateUtil.dayFormat(date)));
                    break;
                case R.id.tv_enroll_date:
                    mEnrollDate = date;
                    mTvEnrollDate.setText(String.format("登记日期：%s", DateUtil.dayFormat(date)));
                    break;
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setDate(Calendar.getInstance())
                .build()
                .show();
    }
}