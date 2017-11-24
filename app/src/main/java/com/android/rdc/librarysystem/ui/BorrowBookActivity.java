package com.android.rdc.librarysystem.ui;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.base.BaseAddActivity;
import com.android.rdc.librarysystem.bean.Borrow;

import java.util.Date;

public class BorrowBookActivity extends BaseAddActivity {
    //谁， 借了哪一本书，什么时候借的？（默认为 插入的表中的时间）

    //1 谁：    手动输入编号/姓名 然后 模糊匹配？显示列表，
        // 一般借书的时候都是拿读者卡，然后刷一下，找出读者的 id，这里没有相应的硬件，那就只能手动输入，输入完整之后提示卡号是否存在

    //2 借了哪一本书： 1. 输入编号/书名 然后匹配  2.手动输入书名？


    @Override
    protected int setLayoutResID() {
        return R.layout.activity_borrow_book;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    private void saveBorrowData() {
        // TODO: 2017/11/24 0024 检查书籍是否存在，是否已被借出，检查读者的借书数量是否已经达到上限

        Borrow borrow = new Borrow();
//        borrow.setBook();
//        borrow.setReader();
        borrow.setBorrowDate(new Date());
        resolveSave(borrow);
    }
}
