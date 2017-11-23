package com.android.rdc.librarysystem.base;

import android.widget.EditText;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;

public abstract class BaseAddActivity extends BaseToolbarActivity {

    protected String getString(EditText et) {
        return et.getText().toString();
    }
}
