package com.android.rdc.librarysystem.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.rdc.amdroidutil.base.BaseFragment;
import com.android.rdc.amdroidutil.listener.OnClickRecyclerViewListener;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.adapter.BookTypeAdapter;
import com.android.rdc.librarysystem.bean.BookType;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;

public class BookTypeFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView mRv;

    public static BookTypeFragment newInstance() {
        return new BookTypeFragment();
    }


    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_book_type;
    }

    @Override
    protected void initData(Bundle bundle) {
        List<BookType> bookTypeList = DataSupport.findAll(BookType.class);
        BookTypeAdapter adapter = new BookTypeAdapter();
        adapter.updateData(bookTypeList);
        adapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int i) {
                BookType bookType = adapter.getDataList().get(i);
                bookType.setSelected(!bookType.isSelected());
                adapter.notifyItemChanged(i);
            }

            @Override
            public boolean onItemLongClick(int i) {
                adapter.getDataList().get(i).setSelected(true);
                adapter.setShowCheckBox(true);
                return true;
            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRv.setAdapter(adapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

}
