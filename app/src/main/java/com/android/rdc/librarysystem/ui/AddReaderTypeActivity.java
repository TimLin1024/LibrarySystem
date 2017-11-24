package com.android.rdc.librarysystem.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.base.BaseAddActivity;
import com.android.rdc.librarysystem.bean.ReaderType;
import com.android.rdc.librarysystem.util.DateUtil;
import com.bigkoo.pickerview.TimePickerView;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加读者类型
 */
public class AddReaderTypeActivity extends BaseAddActivity {

    @BindView(R.id.et_reader_category_name)
    EditText mEtReaderCategoryName;
    @BindView(R.id.et_borrow_count)
    EditText mEtBorrowCount;
    @BindView(R.id.et_borrow_len)
    EditText mEtBorrowLen;
    @BindView(R.id.tv_expire_date)
    TextView mTvExpireDate;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.btn_add_reader_type)
    Button mBtnAddReaderType;

    private Date mExpDate;//「到期时间」

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_add_reader_type;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTitle(R.string.add_reader_type);
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.tv_expire_date, R.id.btn_add_reader_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_expire_date:
                showDatePicker();
                break;
            case R.id.btn_add_reader_type:
                ReaderType readerType = new ReaderType();
                readerType.setTypeName(getString(mEtReaderCategoryName));
                readerType.setBorrowCount(getNumberFromEt(mEtBorrowCount, 5));//若为空 默认为 5 本
                readerType.setBorrowMon(getNumberFromEt(mEtBorrowLen, 1));//如果为空默认为 1 个月
                readerType.setRemark(getString(mEtRemark));
                readerType.setExpDate(mExpDate);
                resolveSave(readerType);
                break;
        }
    }

    protected void showDatePicker() {
        new TimePickerView.Builder(this, (date, v1) -> {
            mExpDate = date;
            mTvExpireDate.setText(String.format("失效日期：%s", DateUtil.dayFormat(date)));
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setDate(Calendar.getInstance())
                .build()
                .show();
    }
}
