package com.android.rdc.librarysystem.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.android.rdc.amdroidutil.base.BaseFragment;
import com.android.rdc.amdroidutil.listener.OnClickRecyclerViewListener;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.adapter.ReaderTypeAdapter;
import com.android.rdc.librarysystem.bean.ClickType;
import com.android.rdc.librarysystem.bean.ReaderType;
import com.android.rdc.librarysystem.bean.ShowSelectAll;
import com.android.rdc.librarysystem.ui.ModifyReaderTypeActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ReaderTypeFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.ll_delete)
    LinearLayout mLlDelete;

    private List<ReaderType> mReaderTypeList;
    private ReaderTypeAdapter mAdapter;

    public static ReaderTypeFragment newInstance() {
        return new ReaderTypeFragment();
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_book_type;
    }

    @Override
    protected void initData(Bundle bundle) {
        mReaderTypeList = DataSupport.findAll(ReaderType.class);
        mAdapter = new ReaderTypeAdapter();
        mAdapter.updateData(mReaderTypeList);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRv.setAdapter(mAdapter);
        setRvClickListener();
    }

    private void setRvClickListener() {
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            @Override
            public void onItemClick(int i) {
                ReaderType readerType = mAdapter.getDataList().get(i);
                if (mLlDelete.getVisibility() == View.VISIBLE) {//下方的删除布局可见，说明是批量操作状态
                    readerType.setSelected(!readerType.isSelected());
                    mAdapter.notifyItemChanged(i);
                } else {//否则打开类型详情
                    ModifyReaderTypeActivity.startActivity(getActivity(),
                            readerType.getId());
                }
            }

            @Override
            public boolean onItemLongClick(int i) {
                if (mLlDelete.getVisibility() == View.VISIBLE) {//目前已经显示了批量处理界面
                    ReaderType readerType = mAdapter.getDataList().get(i);
                    readerType.setSelected(!readerType.isSelected());
                    mAdapter.notifyItemChanged(i);
                    return true;
                }
                mAdapter.getDataList().get(i).setSelected(true);
                mAdapter.setShowCheckBox(true);
                mLlDelete.setVisibility(View.VISIBLE);
                for (ReaderType readerType : mReaderTypeList) {
                    readerType.setSelected(false);
                }
                EventBus.getDefault().post(new ShowSelectAll());
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

        for (ReaderType ReaderType : mReaderTypeList) {
            ReaderType.setSelected(clickType == ClickType.SELECT_ALL);
        }
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_delete)
    public void onViewClicked() {
        new AlertDialog.Builder(getActivity())
                .setMessage("该操作将删除与选中类型相关联的所有数据，确定删除？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    for (ReaderType ReaderType : mReaderTypeList) {
                        if (ReaderType.isSelected()) {
                            DataSupport.delete(ReaderType.class, ReaderType.getId());
                        }
                    }
                    mReaderTypeList = DataSupport.findAll(ReaderType.class);
                    mAdapter.updateData(mReaderTypeList);
                    mAdapter.notifyDataSetChanged();
                })
                .show();
    }
}
