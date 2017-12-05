package com.android.rdc.librarysystem.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.android.rdc.librarysystem.contract.ModifyReaderContract;
import com.android.rdc.librarysystem.presenter.ModifyReaderPresenter;
import com.android.rdc.librarysystem.util.DateUtil;
import com.bigkoo.pickerview.TimePickerView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyReaderActivity extends BaseAddActivity implements ModifyReaderContract.View {

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

    private ModifyReaderContract.Presenter mPresenter;
    private long mReaderId;
    private static final String KEY_READER_ID = "READER_ID";
    private Reader mReader;
    private List<ReaderType> mReaderTypeList;
    private Date mEnrollDate;

    public static void startActivity(Context context, long id) {
        Intent intent = new Intent(context, ModifyReaderActivity.class);
        intent.putExtra(KEY_READER_ID, id);
        context.startActivity(intent);
    }


    @Override
    protected int setLayoutResID() {
        return R.layout.activity_modify_reader;
    }

    @Override
    protected void initData() {
        mPresenter = new ModifyReaderPresenter(this);
        mReaderId = getIntent().getLongExtra(KEY_READER_ID, -1);
    }

    @Override
    protected void initView() {
        setTitle("修改读者信息");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume(mReaderId);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void displayReaderData(Reader reader) {
        mReader = reader;
        mEnrollDate = reader.getEnrollDate();

        mEtReaderName.setText(reader.getName());
        initSpinnerSelection();
        mEtCompanyName.setText(reader.getCompany());
        if (reader.getGender().equals("男")) {
            mRbMan.setChecked(true);
        } else {
            mRbWomen.setChecked(true);
        }
        mEtHomeAddress.setText(reader.getAddress());
        mEtPhone.setText(reader.getPhoneNum());
        mEtEmail.setText(reader.getEmail());
        mTvEnrollDate.setText(String.format("登记日期：%s", DateUtil.dayFormat(mEnrollDate)));
        mEtRemark.setText(reader.getRemark());
    }

    private void initSpinnerSelection() {
        initSpinnerData();
        // 初始化 spinner  选中的位置
        long readerTypeId = 0;
        Cursor cursor = DataSupport.findBySQL("select readertype_id from reader where id = " + mReaderId);
        if (cursor != null && cursor.moveToFirst()) {
            readerTypeId = cursor.getLong(0);
        }
        int index = 0;
        for (ReaderType readerType : mReaderTypeList) {
            if (readerType.getId() == readerTypeId) {
                break;
            }
            index++;
        }
        mSpReaderType.setSelection(index + 1);//加一，因为前面增加了「请选择项」
    }

    private void initSpinnerData() {
        mReaderTypeList = DataSupport.findAll(ReaderType.class);
        List<String> typeNameList = new ArrayList<>();
        typeNameList.add("请选择");
        for (ReaderType readerType : mReaderTypeList) {
            typeNameList.add(readerType.getTypeName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, typeNameList);
        mSpReaderType.setAdapter(arrayAdapter);
    }

    @Override
    public void showReaderDeleteResult(int rowAffect) {
        showToast("删除成功 " + rowAffect + " 行受影响");
        finish();
    }

    @Override
    public void showReaderModifyResult(int rowAffect) {
        showToast("修改成功 " + rowAffect + " 行受影响");
        finish();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick({R.id.iv_delete, R.id.btn_modify_reader, R.id.tv_enroll_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_delete:
                new AlertDialog.Builder(this)
                        .setMessage("确定删除" + (mReader == null ? "" : mReader.getName()) + "?")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", (dialog, which) -> {
                            mPresenter.deleteReader(mReaderId);
                        }).show();
                break;
            case R.id.tv_enroll_date:
                showDatePicker();
                break;
            case R.id.btn_modify_reader:
                modifyReader();
                break;
        }
    }

    private void modifyReader() {
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
        Reader reader = DataSupport.find(Reader.class, mReaderId);
        reader.setName(getString(mEtReaderName));
        reader.setPhoneNum(getString(mEtPhone));
        reader.setAddress(getString(mEtHomeAddress));
        reader.setCompany(getString(mEtCompanyName));
        reader.setEmail(getString(mEtEmail));
        reader.setEnrollDate(mEnrollDate);
        reader.setGender(mRbMan.isChecked() ? "男" : "女");
        reader.setRemark(getString(mEtRemark));
        reader.setReaderType(mReaderTypeList.get(mSpReaderType.getSelectedItemPosition() - 1));//这里要减一，因为前面手动添加了一个提示项
        mPresenter.modifyReader(reader);
    }

    private void showDatePicker() {
        hideSoftInput();//隐藏软键盘，防止遮挡日期选择框
        new TimePickerView.Builder(this, (date, v1) -> {
            mEnrollDate = date;
            mTvEnrollDate.setText(String.format("登记日期：%s", DateUtil.dayFormat(date)));
        }).setDate(Calendar.getInstance())
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .build()
                .show();
    }
}
