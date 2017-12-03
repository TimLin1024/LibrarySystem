package com.android.rdc.librarysystem.ui;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.base.BaseAddActivity;
import com.android.rdc.librarysystem.bean.BookType;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyBookTypeActivity extends BaseAddActivity {

    @BindView(R.id.et_book_category_name)
    EditText mEtBookCategoryName;
    @BindView(R.id.et_keyword)
    EditText mEtKeyword;
    @BindView(R.id.et_remark)
    EditText mEtRemark;

    private static final String KEY_BOOK_TYPE_ID = "BOOK_TYPE_ID";
    private long mBookTypeId;
    private BookType mBookType;

    public static void startActivity(Context context, long bookTypeId) {
        Intent intent = new Intent(context, ModifyBookTypeActivity.class);
        intent.putExtra(KEY_BOOK_TYPE_ID, bookTypeId);
        context.startActivity(intent);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_modify_book_type;
    }

    @Override
    protected void initData() {
        mBookTypeId = getIntent().getLongExtra(KEY_BOOK_TYPE_ID, -1);
        mBookType = DataSupport.find(BookType.class, mBookTypeId);
    }

    @Override
    protected void initView() {
        setTitle("修改书籍类型信息");
        mEtBookCategoryName.setText(mBookType.getTypeName());
        mEtKeyword.setText(mBookType.getKeyWord());
        mEtRemark.setText(mBookType.getRemark());
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.btn_modify_book_type)
    public void onViewClicked() {
        if (nullCheck(mEtBookCategoryName, "书籍类型")) {
            return;
        }

        //查询同名的书籍类型列表
        List<BookType> bookTypeIdList = DataSupport
                .select("id")
                .where("typeName=?", getString(mEtBookCategoryName))
                .find(BookType.class);

        mBookType.setTypeName(getString(mEtBookCategoryName));
        mBookType.setKeyWord(getString(mEtKeyword));
        mBookType.setRemark(getString(mEtRemark));

        if (bookTypeIdList.size() > 1) {
            showToast("您指定的书籍类型名称已存在");
        } else {
            int rowAffect = mBookType.update(mBookTypeId);
            if (rowAffect > 0) {
                showToast("更新成功 " + rowAffect + " 行受影响");
                finish();
            } else {
                showToast("更新失败，请重试");
            }
        }
    }
}
