package com.android.rdc.librarysystem.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.base.BaseAddActivity;
import com.android.rdc.librarysystem.bean.Reader;
import com.android.rdc.librarysystem.util.DateUtil;
import com.bigkoo.pickerview.TimePickerView;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加读者
 */
public class AddReaderActivity extends BaseAddActivity {

    //    @BindView(R.id.et_reader_id)
//    EditText mEtReaderId;
    @BindView(R.id.et_reader_name)
    EditText mEtReaderName;
    @BindView(R.id.tv_indicator_reader)
    TextView mTvIndicatorReader;
    @BindView(R.id.sp_reader_type)
    Spinner mSpReaderType;
    @BindView(R.id.rb_man)
    RadioButton mRbMan;
    @BindView(R.id.rb_women)
    RadioButton mRbWomen;
    @BindView(R.id.tv_enroll_date)
    TextView mTvEnrollDate;
    @BindView(R.id.et_company_name)
    EditText mEtCompanyName;
    @BindView(R.id.et_home_address)
    EditText mEtHomeAddress;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.btn_add_reader)
    Button mBtnAddReader;

    private Date mEnrollDate;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_add_reader;
    }

    @Override
    protected void initData() {
        setTitle("添加读者");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.btn_add_reader, R.id.tv_enroll_date})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_add_reader:
                insertReader();
                break;
            case R.id.tv_enroll_date:
                showDatePicker();
                break;
        }
    }

    private void showDatePicker() {
        new TimePickerView.Builder(this, (date, v1) -> {
            mEnrollDate = date;
            mTvEnrollDate.setText(DateUtil.dayFormat(date));
        }).setDate(Calendar.getInstance())
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .build()
                .show();
    }

    private void insertReader() {
        Reader reader = new Reader();
        reader.setName(getString(mEtReaderName));
        reader.setPhoneNum(getString(mEtPhone));
        reader.setAddress(getString(mEtHomeAddress));
        reader.setCompany(getString(mEtCompanyName));
        reader.setEmail(getString(mEtEmail));
        reader.setEnrollDate(mEnrollDate);
        reader.setGender(mRbMan.isChecked() ? "男" : "女");
        reader.setRemark(getString(mEtRemark));
//        reader.setReaderType(mSpReaderType.getSelectedItemPosition().);
        if (reader.save()) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "添加失败，请重试", Toast.LENGTH_SHORT).show();
        }
    }
}
