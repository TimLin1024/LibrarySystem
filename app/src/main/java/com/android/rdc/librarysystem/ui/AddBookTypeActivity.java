package com.android.rdc.librarysystem.ui;

import android.app.AlertDialog;
import android.widget.EditText;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.base.BaseAddActivity;
import com.android.rdc.librarysystem.bean.BookType;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加书籍类型
 */
public class AddBookTypeActivity extends BaseAddActivity {

    @BindView(R.id.et_book_category_name)
    EditText mEtBookCategoryName;
    @BindView(R.id.et_keyword)
    EditText mEtKeyword;
    @BindView(R.id.et_remark)
    EditText mEtRemark;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_add_book_type;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTitle(R.string.add_book_type);
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.btn_add_book_type)
    public void onViewClicked() {
        if (nullCheck(mEtBookCategoryName, "书籍类型")) {
            return;
        }

        //查询同名的书籍类型列表
        List<BookType> bookTypeIdList = DataSupport
                .select("id")
                .where("typeName=?", getString(mEtBookCategoryName))
                .find(BookType.class);

        BookType bookType = new BookType();
        bookType.setTypeName(getString(mEtBookCategoryName));
        bookType.setKeyWord(getString(mEtKeyword));
        bookType.setRemark(getString(mEtRemark));

        if (!bookTypeIdList.isEmpty()) {
            new AlertDialog.Builder(this)
                    //书籍类型检测防止重名
                    .setMessage("您指定的书籍类型已存在，是否对其内容进行更新？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        bookType.update(bookTypeIdList.get(0).getId());//对内容进行更新
                        finish();
                    })
                    .setNegativeButton("取消", (dialog, which) -> {
                        finish();
                    })
                    .show();
        } else {
            resolveSave(bookType);
        }
    }
}
