package com.android.rdc.librarysystem.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    private Date mPublishDate;
    private Date mEnrollDate;
    private List<BookType> mBookTypeList;
    private ArrayAdapter<String> mSpArrayAdapter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_add_book;
    }

    @Override
    protected void initData() {

    }

    private void updateBookTypeSpinner() {
        List<String> stringList = new ArrayList<>();
        stringList.add("请选择");
        for (BookType bookType : mBookTypeList) {
            stringList.add(bookType.getTypeName());
        }
        stringList.add("添加新类型");
        if (mSpArrayAdapter == null) {
            mSpArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, stringList);
            mSpBookType.setAdapter(mSpArrayAdapter);
        } else {
            mSpArrayAdapter.clear();//清除旧数据
            mSpArrayAdapter.addAll(stringList);//添加新数据
            mSpArrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initView() {
        setTitle(R.string.add_book);
    }

    @Override
    protected void initListener() {
        mSpBookType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == mBookTypeList.size() + 1) {//如果点击了末尾项，跳转到添加类型页面。因为头部 + 1，后面 +1，所以总长度为 size+2,最后一项下标为 size+1
                    startActivity(AddBookTypeActivity.class);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBookTypeList = DataSupport.findAll(BookType.class);
        updateBookTypeSpinner();
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
                saveBook();
                break;
        }
    }

    private void saveBook() {
        if (mSpBookType.getSelectedItemPosition() <= 0) {
            showToast("请选择书籍类型");
            return;
        }
        if (nullCheck(mEtBookName, "书籍名称")
                || nullCheck(mEtAuthorName, "作者")
                || nullCheck(mEtPressName, "出版社")
                ) {
            return;
        }
        if (mPublishDate == null) {
            showToast("请选择出版日期");
            return;
        }
        if (mEnrollDate == null) {
            showToast("请选择登记日期");
            return;
        }

        if (mEnrollDate.before(mPublishDate)) {
            showToast("登记日期不能早于出版日期");
            return;
        }

        Book book = new Book();
        book.setBookName(getString(mEtBookName));
        book.setBookType(mBookTypeList.get(mSpBookType.getSelectedItemPosition() - 1));//这里要减一，因为前面手动添加了一个提示项
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
    }

    private void showPickerView(TextView tv) {
        hideSoftInput();//隐藏软键盘
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
