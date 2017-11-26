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
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.adapter.BookRvAdapter;
import com.android.rdc.librarysystem.bean.Book;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class QueryBookActivity extends BaseToolbarActivity {
    private static final String TAG = "QueryBookActivity";
    @BindView(R.id.rv)
    RecyclerView mRv;

    private BookRvAdapter mAdapter;
    private SearchView mSearchView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_query_book, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        initSearchView(menuItem);
        return true;
    }

    private void initSearchView(MenuItem menuItem) {
        mSearchView = (SearchView) menuItem.getActionView();
        mSearchView.setQueryHint("输入书名/作者等关键字");
        AutoCompleteTextView autoCompleteTextView = mSearchView.findViewById(R.id.search_src_text);
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(String.valueOf(i));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, arrayList);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(0);
        autoCompleteTextView.setDropDownBackgroundResource(R.color.white);
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {//这里需要自行设置 OnItemClickListener ，因为SearchView 中设置的监听器需要 CursorAdapter 而这里使用 的是 ArrayAdapter
            mSearchView.setQuery(arrayList.get(position), true);
        });
        setQueryListener();
    }

    private void setQueryListener() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String condition = "%" + query + "%";
                List<Book> bookList = DataSupport
                        .where("bookname like ? or authorname like ? or pressname like ? ", condition, condition, condition)//模糊查询书籍
                        .find(Book.class);
                mAdapter.updateData(bookList);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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
        mAdapter = new BookRvAdapter();
        List<Book> bookList = DataSupport.findAll(Book.class);
        mAdapter.updateData(bookList);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {

    }
}
