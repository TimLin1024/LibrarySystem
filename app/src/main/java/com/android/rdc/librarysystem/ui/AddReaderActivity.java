package com.android.rdc.librarysystem.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.bean.Reader;

import org.litepal.tablemanager.Connector;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReaderActivity extends AppCompatActivity {

    @BindView(R.id.et_reader_id)
    EditText mEtReaderId;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reader);
        ButterKnife.bind(this);
        Connector.getDatabase();
    }

    @OnClick(R.id.btn_add_reader)
    public void onViewClicked() {
        Reader reader = new Reader();
        reader.setName(getString(mEtReaderName));
        reader.setPhoneNum(getString(mEtPhone));
        reader.setAddress(getString(mEtHomeAddress));
        reader.setCompany(getString(mEtCompanyName));
        reader.setEmail(getString(mEtEmail));
        reader.setEnrollDate(new Date());

        if (reader.save()) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "添加失败，请重试", Toast.LENGTH_SHORT).show();
        }
    }

    private String getString(EditText et) {
        return et.getText().toString();
    }


}
