package com.android.rdc.librarysystem.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.base.BaseAddActivity;
import com.android.rdc.librarysystem.bean.Book;
import com.android.rdc.librarysystem.bean.BookType;
import com.android.rdc.librarysystem.contract.ModifyBookContract;
import com.android.rdc.librarysystem.presenter.ModifyBookPresenter;
import com.android.rdc.librarysystem.util.DateUtil;
import com.bigkoo.pickerview.TimePickerView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyBookActivity extends BaseAddActivity implements ModifyBookContract.View {
    private static final String TAG = "ModifyBookActivity";
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

    private List<BookType> mBookTypeList;
    private ModifyBookContract.Presenter mPresenter;
    private long mBookId;
    private static final String KEY_BOOK_ID = "BOOK_ID";
    private Date mPublishDate;
    private Date mEnrollDate;
    private Book mBook;

//    public static Intent newIntent(Context context, long bookId) {
//        Intent intent = new Intent(context, ModifyBookActivity.class);
//        intent.putExtra(KEY_BOOK_ID, bookId);
//        return intent;
//    }

    public static void startActivity(Context context, long bookId) {
        Intent intent = new Intent(context, ModifyBookActivity.class);
        intent.putExtra(KEY_BOOK_ID, bookId);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_modify_book;
    }

    @Override
    protected void initData() {
        mBookTypeList = DataSupport.findAll(BookType.class);
        mPresenter = new ModifyBookPresenter(this);
        initBookTypeSpinner();
        mBookId = getIntent().getLongExtra(KEY_BOOK_ID, -1);
    }

    private void initBookTypeSpinner() {
        List<String> stringList = new ArrayList<>();
        stringList.add("请选择");
        for (BookType bookType : mBookTypeList) {
            stringList.add(bookType.getTypeName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, stringList);
        mSpBookType.setAdapter(arrayAdapter);
    }

    @Override
    protected void initView() {
        setTitle("修改书籍信息");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume(mBookId);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void displayBookData(Book book) {
        mBook = book;

        mEtBookName.setText(book.getBookName());
        mEnrollDate = book.getEnrollDate();
        mPublishDate = book.getPublishDate();
        mTvEnrollDate.setText(String.format("登记日期：%s", DateUtil.dayFormat(mEnrollDate)));
        mTvPublishDate.setText(String.format("出版日期：%s", DateUtil.dayFormat(mPublishDate)));
        setBookTypeSelection();
        mEtAuthorName.setText(book.getAuthorName());
        mEtPressName.setText(book.getPressName());
        mEtPrice.setText(String.valueOf(book.getPrice()));
        mEtPageNum.setText(String.valueOf(book.getPages()));
        mEtKeywords.setText(book.getKeyWord());
        mEtRemark.setText(book.getRemark());
    }

    private void setBookTypeSelection() {
        long bookTypeId = -1;
        Cursor cursor = DataSupport.findBySQL("select booktype_id from book where id = " + mBookId);
        //noinspection ConstantConditions
        if (cursor != null && cursor.moveToFirst()) {
            bookTypeId = cursor.getLong(0);//获取图书类型的 id
            Log.d(TAG, "setBookTypeSelection:bookTypeId " + bookTypeId);
        }
        int index = 0;
        for (BookType bookType : mBookTypeList) {
            if (bookTypeId == bookType.getId()) {
                break;
            }
            index++;
        }
        mSpBookType.setSelection(index + 1);//+1 因为前面添加了一个请选择项目
    }

    @Override
    public void showBookDeleteResult(int rowAffect) {
        showToast("删除成功。" + rowAffect + "行受影响");
        finish();
    }

    @Override
    public void showBookModifyResult(int rowAffect) {
        showToast("修改成功。" + rowAffect + "行受影响");
        finish();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick({R.id.tv_publish_date, R.id.tv_enroll_date,
            R.id.btn_modify_book, R.id.iv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_publish_date:
                showPickerView(mTvPublishDate);
                break;
            case R.id.tv_enroll_date:
                showPickerView(mTvEnrollDate);
                break;
            case R.id.btn_modify_book:
                modifyBook();
                break;
            case R.id.iv_delete:
                resolveDelete();
                break;
        }
    }

    private void resolveDelete() {
        new AlertDialog.Builder(this)
                .setMessage("确定删除" + (mBook == null ? "" : mBook.getBookName()) + "?")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    mPresenter.deleteBook(mBookId);
                }).show();
    }

    private void showPickerView(TextView tv) {
        hideSoftInput();
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

    private void modifyBook() {
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

        Book book = DataSupport.find(Book.class, mBookId);
        book.setBookName(getString(mEtBookName));
        book.setBookType(mBookTypeList.get(mSpBookType.getSelectedItemPosition() - 1));//这里要减一，因为前面手动添加了一个提示项
        book.setAuthorName(getString(mEtAuthorName));
        book.setPressName(getString(mEtPressName));
        book.setPublishDate(mPublishDate);
        book.setEnrollDate(mEnrollDate);
        book.setPrice(getNumberFromEt(mEtPrice, 0));
        book.setPages(getNumberFromEt(mEtPageNum, 0));
        book.setKeyWord(getString(mEtKeywords));
//        book.setBorrowed(false);//默认未借出
        book.setRemark(getString(mEtRemark));
        mPresenter.modifyBook(book);
    }
}
