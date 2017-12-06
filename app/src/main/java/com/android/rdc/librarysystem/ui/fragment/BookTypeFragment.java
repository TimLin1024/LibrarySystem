package com.android.rdc.librarysystem.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.rdc.amdroidutil.base.BaseFragment;
import com.android.rdc.amdroidutil.listener.OnClickRecyclerViewListener;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.adapter.BookTypeAdapter;
import com.android.rdc.librarysystem.bean.BookType;
import com.android.rdc.librarysystem.bean.ClickType;
import com.android.rdc.librarysystem.bean.ShowSelectAll;
import com.android.rdc.librarysystem.ui.ModifyBookTypeActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BookTypeFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.ll_delete)
    LinearLayout mLlDelete;
    @BindView(R.id.iv_delete)
    ImageView mIvDelete;

    private List<BookType> mBookTypeList;
    private BookTypeAdapter mAdapter;

    public static BookTypeFragment newInstance() {
        return new BookTypeFragment();
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_book_type;
    }

    @Override
    protected void initData(Bundle bundle) {
        mBookTypeList = DataSupport.findAll(BookType.class);
        mAdapter = new BookTypeAdapter();
        mAdapter.updateData(mBookTypeList);
        setRvClickListener();
        mRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
    }

    private void setRvClickListener() {
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int i) {
                BookType bookType = mAdapter.getDataList().get(i);
                if (mLlDelete.getVisibility() == View.VISIBLE) {
                    bookType.setSelected(!bookType.isSelected());
                    mAdapter.notifyItemChanged(i);
                } else {
                    ModifyBookTypeActivity.startActivity(getActivity(), bookType.getId());
                }
            }

            @Override
            public boolean onItemLongClick(int i) {
                if (mLlDelete.getVisibility() == View.VISIBLE) {//目前已经显示了批量处理界面
                    BookType bookType = mAdapter.getDataList().get(i);
                    bookType.setSelected(!bookType.isSelected());
                    mAdapter.notifyItemChanged(i);
                    return true;
                }
                mAdapter.getDataList().get(i).setSelected(true);
                mAdapter.setShowCheckBox(true);
                mLlDelete.setVisibility(View.VISIBLE);
                for (BookType bookType : mBookTypeList) {//批量操作开始时默认没有选中任何项
                    bookType.setSelected(false);
                }
                EventBus.getDefault().post(new ShowSelectAll());//发送
                return true;
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    public void onSelectAllClick(Enum<ClickType> clickType) {
        if (clickType == ClickType.CANCEL) {
            mLlDelete.setVisibility(View.GONE);
            mAdapter.setShowCheckBox(false);//内部自动调用 notifyDataSetChanged
            return;
        }

        for (BookType bookType : mBookTypeList) {
            bookType.setSelected(clickType == ClickType.SELECT_ALL);
        }
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_delete)
    public void onViewClicked() {
        showAlertDialog();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(getActivity())
                .setMessage("该操作将删除与选中类型相关联的所有数据，确定删除？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    for (BookType bookType : mBookTypeList) {
                        if (bookType.isSelected()) {
                            DataSupport.delete(BookType.class, bookType.getId());
                        }
                    }
                    mBookTypeList = DataSupport.findAll(BookType.class);
                    mAdapter.updateData(mBookTypeList);
                    mAdapter.notifyDataSetChanged();
                })
                .show();
    }
}
