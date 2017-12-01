package com.android.rdc.librarysystem.ui;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.amdroidutil.listener.OnClickRecyclerViewListener;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.adapter.ReaderRvAdapter;
import com.android.rdc.librarysystem.bean.Reader;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;

public class QueryReaderActivity extends BaseToolbarActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;
    private SearchView mSearchView;
    private ReaderRvAdapter mAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_query_reader, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        mSearchView = (SearchView) menuItem.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.menu.menu_query_reader:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_query_reader;
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

    @Override
    protected void onResume() {
        super.onResume();
        List<Reader> readerList = DataSupport.findAll(Reader.class);
        if (mAdapter == null) {
            mAdapter = new ReaderRvAdapter();
            mAdapter.updateData(readerList);
            mRv.setAdapter(mAdapter);
            mRv.setLayoutManager(new LinearLayoutManager(this));
            mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
                @Override
                public void onItemClick(int i) {
                    showItemDialog(i);
                }

                @Override
                public boolean onItemLongClick(int i) {
                    return false;
                }
            });
        } else {
            mAdapter.updateData(readerList);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void showItemDialog(int i) {
        new AlertDialog.Builder(QueryReaderActivity.this)
                .setItems(new String[]{"修改读者信息", "查询借阅信息"}, (dialog, which) -> {
                    long readerId = mAdapter.getDataList().get(i).getId();
                    switch (which) {
                        case 0:
                            ModifyReaderActivity.startActivity(QueryReaderActivity.this, readerId);
                            break;
                        case 1:
                            ReaderBorrowInfoActivity.startActivity(QueryReaderActivity.this, readerId);
                            break;
                    }
                })
                .show();
    }
}
