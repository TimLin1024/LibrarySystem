package com.android.rdc.librarysystem.ui;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.amdroidutil.listener.OnClickRecyclerViewListener;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.adapter.BookRvAdapter;
import com.android.rdc.librarysystem.bean.Book;
import com.android.rdc.librarysystem.dao.RecordDao;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class QueryBookActivity extends BaseToolbarActivity {
    private static final String TAG = "QueryBookActivity";
    @BindView(R.id.rv)
    RecyclerView mRv;

    private BookRvAdapter mRvAdapter;
    private SearchView mSearchView;
    private RecordDao mRecordDao;
    private List<String> mRecordList = new ArrayList<>();
    private ArrayAdapter<String> mArrayAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_query_book, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        initSearchView(menuItem);
        return true;
    }

    private void initSearchView(MenuItem menuItem) {
        mRecordDao = RecordDao.getInstance(getApplicationContext());
        mSearchView = (SearchView) menuItem.getActionView();
        mSearchView.setQueryHint("输入书名/作者等关键字");
        initAutoCompleteTv();
        setQueryListener();
    }

    private void initAutoCompleteTv() {
        updateRecordList();
        mArrayAdapter = new ArrayAdapter<>(this, R.layout.item_record, mRecordList);
        AutoCompleteTextView autoCompleteTextView = mSearchView.findViewById(R.id.search_src_text);
        autoCompleteTextView.setAdapter(mArrayAdapter);
        autoCompleteTextView.setThreshold(0);
        autoCompleteTextView.setDropDownBackgroundResource(R.color.white);
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {//这里需要自行设置 OnItemClickListener ，因为SearchView 中设置的监听器需要 CursorAdapter 而这里使用 的是 ArrayAdapter
            if (position == mRecordList.size() - 1) {//若点击的是底部项，删除所有记录
                mRecordDao.deleteAllRecord();
                //更新数据
                updateRecordList();
                mArrayAdapter.clear();//清空数据
            } else {
                mSearchView.setQuery(mRecordList.get(position), true);
            }
        });
    }

    private void updateRecordList() {
        mRecordList.clear();
        mRecordList.addAll(mRecordDao.queryRecord());//查询记录列表
        if (!mRecordList.isEmpty()) {
            mRecordList.add("清除所有记录");
        }
    }

    private void setQueryListener() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                resolveQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void resolveQuery(String query) {
        //不插入重复记录
        mRecordDao.deleteRecord(query);//删除旧记录（如果有的话）
        mRecordDao.insert(query);//插入新的查询纪录
        BookQueryResultActivity.startActivity(this, query);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.menu.menu_query_book:
                return true;
//            case R.menu.show_has_borrow:
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_query_book;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTitle("书籍查询");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Book> bookList = DataSupport.findAll(Book.class);//默认显示所有的书籍
        updateBookList(bookList);
    }

    private void updateBookList(List<Book> bookList) {
        if (mRvAdapter == null) {
            mRvAdapter = new BookRvAdapter();
            mRvAdapter.updateData(bookList);
            mRv.setLayoutManager(new LinearLayoutManager(this));
            mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            mRv.setAdapter(mRvAdapter);
            mRvAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
                @Override
                public void onItemClick(int i) {
                    ModifyBookActivity.startActivity(QueryBookActivity.this,
                            mRvAdapter.getDataList().get(i).getId());
                }

                @Override
                public boolean onItemLongClick(int i) {
                    return false;
                }
            });
        } else {
            mRvAdapter.updateData(bookList);
            mRvAdapter.notifyDataSetChanged();
        }
    }
}
