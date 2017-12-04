package com.android.rdc.librarysystem.ui;

import android.content.ContentValues;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.base.BaseAddActivity;
import com.android.rdc.librarysystem.bean.Book;
import com.android.rdc.librarysystem.bean.Borrow;
import com.android.rdc.librarysystem.bean.Reader;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ReturnBookActivity extends BaseAddActivity {
    private static final String TAG = "ReturnBookActivity";
    @BindView(R.id.tv_auto_complete_reader_id)
    AppCompatAutoCompleteTextView mTvAutoCompleteReaderId;
    @BindView(R.id.tv_auto_complete_book_id)
    AppCompatAutoCompleteTextView mTvAutoCompleteBookId;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_return_book;
    }

    @Override
    protected void initData() {
        initBookIdAutoComplete();
        initReaderIdAutoComplete();
    }

    @Override
    protected void initView() {
        setTitle("还书");
    }

    @Override
    protected void initListener() {

    }

    @OnClick(R.id.btn_return)
    public void onViewClicked() {
        saveReturnData();
    }

    /**
     * 还书。先找到原来的记录增加借书日期，其他的内容不需要修改
     */
    private void saveReturnData() {
        Reader reader = DataSupport.find(Reader.class, (long) getNumberFromEt(mTvAutoCompleteReaderId, -1));
        if (reader == null) {
            showToast("该借书证号不存在，请检查");
            return;
        }
        Book book = DataSupport.find(Book.class, (long) getNumberFromEt(mTvAutoCompleteBookId, -1));
        if (book == null) {
            showToast("该图书编号不存在，请检查");
            return;
        }
        if (!book.isBorrowed()) {//目前没有被借，说明已还
            showToast("该书在馆，无需归还");
            return;
        }
        //查找对应的记录
        Borrow borrowRecord = getBorrowRecord(reader, book);
        if (borrowRecord == null) {
            showToast("您已经归还此书");
            return;
        }
        //进行保存
        doSave(reader, book, borrowRecord);
    }

    private void doSave(Reader reader, Book book, Borrow borrowRecord) {
        Connector.getDatabase().beginTransaction();//开启事务
        //更新读者信息
        ContentValues readerCv = new ContentValues();
        readerCv.put("currentborrowcount", reader.getCurrentBorrowCount() - 1);
        DataSupport.update(Reader.class, readerCv, reader.getId());
        //更新书籍信息
        ContentValues bookCv = new ContentValues();
        bookCv.put("isborrowed", false);
        DataSupport.update(Book.class, bookCv, book.getId());
        //更新借阅信息
        borrowRecord.setReturnDate(new Date());//归还日期
        borrowRecord.setReader(reader);
        borrowRecord.setBook(book);
        if (borrowRecord.save()) {
            Connector.getDatabase().setTransactionSuccessful();//设置事务成功
            Toast.makeText(this, "还书成功", Toast.LENGTH_SHORT).show();
            Connector.getDatabase().endTransaction();//结束事务
            finish();
        } else {
            Connector.getDatabase().endTransaction();//结束事务
            Toast.makeText(this, "还书失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    private Borrow getBorrowRecord(Reader reader, Book book) {
        List<Borrow> borrowList = DataSupport
                .where("book_id = ? and reader_id=?", String.valueOf(book.getId()), String.valueOf(reader.getId()))
                .find(Borrow.class);
        Borrow borrowRecord = null;
        for (Borrow borrow : borrowList) {
            if (borrow.getReturnDate() == null) {//还书日期为空，说明未还
                borrowRecord = borrow;
            }
        }
        return borrowRecord;
    }

    private void initBookIdAutoComplete() {
        List<Book> bookList = DataSupport.select("id").find(Book.class);
        List<String> bookIdList = new ArrayList<>();
        for (Book book : bookList) {
            bookIdList.add(String.valueOf(book.getId()));
        }
        ArrayAdapter<String> bookIdAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, bookIdList);
        mTvAutoCompleteBookId.setAdapter(bookIdAdapter);
    }

    private void initReaderIdAutoComplete() {
        List<Reader> readerList = DataSupport.findAll(Reader.class);
        List<String> readerNameList = new ArrayList<>();
        for (Reader reader : readerList) {
            readerNameList.add(String.valueOf(reader.getId()));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, readerNameList);
        mTvAutoCompleteReaderId.setAdapter(arrayAdapter);
    }

}
