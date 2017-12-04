package com.android.rdc.librarysystem.ui;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.base.BaseAddActivity;
import com.android.rdc.librarysystem.bean.Reader;
import com.android.rdc.librarysystem.bean.ReaderType;
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
 * 添加读者
 */
public class AddReaderActivity extends BaseAddActivity {

    @BindView(R.id.et_reader_name)
    EditText mEtReaderName;
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

    private Date mEnrollDate;
    private List<ReaderType> mReaderTypeList;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_add_reader;
    }

    @Override
    protected void initData() {
        mReaderTypeList = DataSupport.findAll(ReaderType.class);
    }

    @Override
    protected void initView() {
        setTitle("添加读者");
    }

    @Override
    protected void initListener() {
        List<String> typeNameList = new ArrayList<>();
        typeNameList.add("请选择");
        for (ReaderType readerType : mReaderTypeList) {
            typeNameList.add(readerType.getTypeName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, typeNameList);
        mSpReaderType.setAdapter(arrayAdapter);
    }

    @OnClick({R.id.btn_add_reader, R.id.tv_enroll_date})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_add_reader:
                saveReader();
                break;
            case R.id.tv_enroll_date:
                showDatePicker();
                break;
        }
    }

    private void showDatePicker() {
        hideSoftInput();//隐藏软键盘
        new TimePickerView.Builder(this, (date, v1) -> {
            mEnrollDate = date;
            mTvEnrollDate.setText(String.format("登记日期：%s", DateUtil.dayFormat(date)));
        }).setDate(Calendar.getInstance())
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .build()
                .show();
    }

    private void saveReader() {
        if (mSpReaderType.getSelectedItemPosition() <= 0) {
            showToast("请选择读者类型");
            return;
        }

        if (nullCheck(mEtReaderName, "读者姓名")
                || nullCheck(mEtPhone, "联系方式")) {
            return;
        }

        if (mEnrollDate == null) {
            showToast("请选择登记日期");
            return;
        }

        Reader reader = new Reader();
        reader.setName(getString(mEtReaderName));
        reader.setPhoneNum(getString(mEtPhone));
        reader.setAddress(getString(mEtHomeAddress));
        reader.setCompany(getString(mEtCompanyName));
        reader.setEmail(getString(mEtEmail));
        reader.setEnrollDate(mEnrollDate);
        reader.setGender(mRbMan.isChecked() ? "男" : "女");
        reader.setRemark(getString(mEtRemark));

        reader.setReaderType(mReaderTypeList.get(mSpReaderType.getSelectedItemPosition() - 1));//这里要减一，因为前面手动添加了一个提示项
        resolveSave(reader);
    }
}
