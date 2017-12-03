package com.android.rdc.librarysystem.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.base.BaseAddActivity;
import com.android.rdc.librarysystem.bean.ReaderType;
import com.android.rdc.librarysystem.util.DateUtil;
import com.bigkoo.pickerview.TimePickerView;

import org.litepal.crud.DataSupport;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyReaderTypeActivity extends BaseAddActivity {

    private static final String KEY_READER_TYPE_ID = "READER_TYPE_ID";
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

    private Date mExpDate;//「到期时间」
    private long mReaderTypeId;
    private ReaderType mReaderType;

    public static void startActivity(Context context, long readerTypeId) {
        Intent intent = new Intent(context, ModifyReaderTypeActivity.class);
        intent.putExtra(KEY_READER_TYPE_ID, readerTypeId);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_modify_reader_type;
    }

    @Override
    protected void initData() {
        mReaderTypeId = getIntent().getLongExtra(KEY_READER_TYPE_ID, -1);
        mReaderType = DataSupport.find(ReaderType.class, mReaderTypeId);
    }

    @Override
    protected void initView() {
        setTitle("读者类型修改");
        mExpDate = mReaderType.getExpDate();
        mEtReaderCategoryName.setText(mReaderType.getTypeName());
        mEtBorrowCount.setText(String.valueOf(mReaderType.getBorrowCount()));
        mEtBorrowLen.setText(String.valueOf(mReaderType.getBorrowMon()));
        mTvExpireDate.setText(String.format("失效日期：%s", DateUtil.dayFormat(mExpDate)));
        mEtRemark.setText(mReaderType.getRemark());
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
                saveReaderType();
                break;
        }
    }


    private void saveReaderType() {
        if (nullCheck(mEtReaderCategoryName, "读者类型名称")
                || nullCheck(mEtBorrowCount, "最大借书数量")
                || nullCheck(mEtBorrowLen, "借书期限")) {
            return;
        }

        if (mExpDate == null) {
            showToast("请设置失效日期");
            return;
        }

        mReaderType.setTypeName(getString(mEtReaderCategoryName));
        mReaderType.setBorrowCount(getNumberFromEt(mEtBorrowCount, 5));//若为空 默认为 5 本
        mReaderType.setBorrowMon(getNumberFromEt(mEtBorrowLen, 1));//如果为空默认为 1 个月
        mReaderType.setRemark(getString(mEtRemark));
        mReaderType.setExpDate(mExpDate);

        int rowAffect = mReaderType.update(mReaderTypeId);
        if (rowAffect > 0) {
            showToast("更新成功 " + rowAffect + " 行受影响");
            finish();
        } else {
            showToast("更新失败，请重试");
        }

//        ContentValues contentValues = new ContentValues();
//        contentValues.put("typename", mReaderType.getTypeName());
//        contentValues.put("borrowcount",mReaderType.getBorrowCount());
//        contentValues.put("borrowmon",mReaderType.getBorrowCount());
//        DataSupport.update(ReaderType.class, contentValues, mReaderTypeId);
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
