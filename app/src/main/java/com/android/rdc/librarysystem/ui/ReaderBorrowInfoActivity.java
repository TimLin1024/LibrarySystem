package com.android.rdc.librarysystem.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.amdroidutil.listener.OnClickRecyclerViewListener;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.adapter.ReaderBorrowInfoRvAdapter;
import com.android.rdc.librarysystem.bean.Reader;
import com.android.rdc.librarysystem.bean.ReaderBorrowInfo;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReaderBorrowInfoActivity extends BaseToolbarActivity {

    private static final String KEY_READER_ID = "READER_ID";
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.ll_no_match_result)
    LinearLayout mLlNoMatchResult;
    private long mReaderId;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_reader_borrow_info;
    }

    @Override
    protected void initData() {
        mReaderId = getIntent().getLongExtra(KEY_READER_ID, -1);
    }

    @Override
    protected void initView() {
        setTitle("读者借阅信息");
        List<ReaderBorrowInfo> readerBorrowInfoList = getReaderInfoList();
        Reader reader = DataSupport.find(Reader.class, mReaderId);
        getSupportActionBar().setSubtitle(reader.getName() + "借阅了 " + readerBorrowInfoList.size() + " 本书");

        if (readerBorrowInfoList.isEmpty()) {
            mRv.setVisibility(View.GONE);
            mLlNoMatchResult.setVisibility(View.VISIBLE);
            return;
        }

        ReaderBorrowInfoRvAdapter adapter = new ReaderBorrowInfoRvAdapter();
        readerBorrowInfoList.size();
        adapter.updateData(getReaderInfoList());
        mRv.setAdapter(adapter);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int i) {
                ModifyBookActivity.startActivity(ReaderBorrowInfoActivity.this,
                        adapter.getDataList().get(i).getBookId());//点击跳转到书籍修改页
            }

            @Override
            public boolean onItemLongClick(int i) {
                return false;
            }
        });
    }

    @Override
    protected void initListener() {

    }

    private List<ReaderBorrowInfo> getReaderInfoList() {
        //根据读者 id ，查询所借阅所有的书籍 ,首先去 borrow 表中查询 该 id 借阅的所有书籍的 id，根据 bookId 去 book 表中查询所有对应的 book
        Cursor cursor = DataSupport.findBySQL("select book_id,bookname,authorname,borrowdate,returndate from borrow,book where book.id = borrow.book_id and reader_id = " + mReaderId);
        List<ReaderBorrowInfo> readerBorrowInfoList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            readerBorrowInfoList.add(getReaderBorrowBeanFromCursor(cursor));
            while (cursor.moveToNext()) {
                readerBorrowInfoList.add(getReaderBorrowBeanFromCursor(cursor));
            }
        }
        return readerBorrowInfoList;
    }

    public static void startActivity(Context context, long readerId) {
        Intent intent = new Intent(context, ReaderBorrowInfoActivity.class);
        intent.putExtra(KEY_READER_ID, readerId);
        context.startActivity(intent);
    }

    private ReaderBorrowInfo getReaderBorrowBeanFromCursor(Cursor cursor) {
        return new ReaderBorrowInfo()
                .setBookId(cursor.getLong(0))
                .setBookMsg(cursor.getString(1) +" / "+ cursor.getString(2))
                .setBorrowDateTime(cursor.getLong(3))
                .setReturnDateTime(cursor.getLong(4));
    }

}
