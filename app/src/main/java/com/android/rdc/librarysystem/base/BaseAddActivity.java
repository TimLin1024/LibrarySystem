package com.android.rdc.librarysystem.base;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;

import org.litepal.crud.DataSupport;

public abstract class BaseAddActivity extends BaseToolbarActivity {

    protected String getString(EditText et) {
        return et.getText().toString();
    }

    protected void resolveSave(DataSupport dataSupport) {
        if (dataSupport.save()) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "添加失败，请重试", Toast.LENGTH_SHORT).show();
        }
    }

    protected void resolveSave(DataSupport dataSupport, String successMsg, String errMsg) {
        if (dataSupport.save()) {
            Toast.makeText(this, successMsg, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
        }
    }

    protected int getNumberFromEt(EditText et, int defaultValue) {
        String str = getString(et);
        return TextUtils.isEmpty(str) ? defaultValue : Integer.parseInt(str);
    }

    protected boolean nullCheck(EditText et, String msg) {
        if (TextUtils.isEmpty(getString(et))) {
            showToast(msg + "不能为空");
            return true;
        }
        return false;
    }
}
