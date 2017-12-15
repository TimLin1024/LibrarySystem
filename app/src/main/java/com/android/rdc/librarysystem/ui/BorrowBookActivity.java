package com.android.rdc.librarysystem.ui;

import android.database.Cursor;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.widget.ArrayAdapter;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.base.BaseAddActivity;
import com.android.rdc.librarysystem.bean.Book;
import com.android.rdc.librarysystem.bean.Borrow;
import com.android.rdc.librarysystem.bean.Reader;
import com.android.rdc.librarysystem.bean.ReaderType;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BorrowBookActivity extends BaseAddActivity {
    private static final String TAG = "BorrowBookActivity";
    @BindView(R.id.tv_auto_complete_reader_id)
    AppCompatAutoCompleteTextView mTvAcReaderId;
    @BindView(R.id.tv_auto_complete_book_id)
    AppCompatAutoCompleteTextView mTvAutoCompleteBookId;
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
        setTitle("图书借阅");

        initReaderIdAutoComplete();
        initBookIdAutoComplete();
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.btn_borrow)
    public void onViewClicked() {
        saveBorrowData();
    }

    private void saveBorrowData() {
        // 检查书籍是否存在，是否已被借出，检查读者的借书数量是否已经达到上限
        Reader reader = DataSupport.find(Reader.class, (long) getNumberFromEt(mTvAcReaderId, -1));
        if (reader == null) {
            showToast("该借书证号不存在，请检查输入");
            return;
        }
        Book book = DataSupport.find(Book.class, (long) getNumberFromEt(mTvAutoCompleteBookId, -1));
        if (book == null) {
            showToast("该图书编号不存在，请检查输入");
            return;
        }
        if (book.isBorrowed()) {
            showToast("抱歉，图书已经借出");
            return;
        }

        int maxBorrowCount = getMaxBorrowCount(reader);
        if (maxBorrowCount <= reader.getCurrentBorrowCount()) {
            showToast("您的借书数量已达上限，请先还书");
            return;
        }

        doSave(reader, book);
    }

    /**
     * 获取该读者身份所允许的最大借书数量
     * */
    private int getMaxBorrowCount(Reader reader) {
        int maxBorrowCount = 4;//最大借书数量（本科生为 4 本，其他都大于该值）
        //先找到 readertype_id,然后再根据 readertype_id 找出该读者的最大借书数量
        Cursor cursor = DataSupport.findBySQL(
                "select * " +
                        " from reader " +
                        " where id=" + reader.getId());
        if (cursor != null && cursor.moveToFirst()) {
            int index = cursor.getColumnIndex("readertype_id");
            if (index != -1) {
                ReaderType readerType = DataSupport.find(ReaderType.class, cursor.getLong(index));
                maxBorrowCount = readerType.getBorrowCount();
            }
            cursor.close();
        }
        return maxBorrowCount;
    }

    private void doSave(Reader reader, Book book) {
        reader.increaseCurrentBorrowCount();//当前借书数目 + 1
        reader.update(reader.getId());
        book.setBorrowed(true);
        book.update(book.getId());

        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setReader(reader);
        borrow.setBorrowDate(new Date());
        resolveSave(borrow);
    }

    private void initBookIdAutoComplete() {
        List<Book> bookList = DataSupport.select("id").find(Book.class);
        List<String> bookIdList = new ArrayList<>();
        for (Book book : bookList) {
            bookIdList.add(String.valueOf(book.getId()));
        }
        ArrayAdapter<String> bookIdAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, bookIdList);
        mTvAutoCompleteBookId.setAdapter(bookIdAdapter);
    }

    private void initReaderIdAutoComplete() {
        List<Reader> readerList = DataSupport.findAll(Reader.class);
        List<String> readerNameList = new ArrayList<>();
        for (Reader reader : readerList) {
            readerNameList.add(String.valueOf(reader.getId()));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, readerNameList);
        mTvAcReaderId.setAdapter(arrayAdapter);
    }
}
