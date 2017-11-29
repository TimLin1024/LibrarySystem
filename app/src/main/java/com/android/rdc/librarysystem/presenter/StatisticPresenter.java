package com.android.rdc.librarysystem.presenter;

import com.android.rdc.librarysystem.contract.StatisticContract;
import com.android.rdc.librarysystem.model.StatisticModel;
import com.github.mikephil.charting.data.PieData;

public class StatisticPresenter implements StatisticContract.Presenter, StatisticContract.Model.OnStatisticFetchListener {
    private StatisticContract.Model mModel;
    private StatisticContract.View mView;

    public StatisticPresenter(StatisticContract.View view) {
        mView = view;
        mModel = new StatisticModel();
    }

    @Override
    public void onResume() {
        if (mView != null) {
            mView.showProgress();//显示进度
        }
        mModel.getBookTypeData(this);
        mModel.getStoreInfoData(this);
    }


    @Override
    public void onDestroy() {
        mView = null;//解除引用
    }

    @Override
    public void onFetchBookTypeData(PieData pieData) {
        mView.hideProgress();
        mView.updateBookTypeChart(pieData);
    }

    @Override
    public void onFetchStoreInfoData(PieData pieData) {
        mView.hideProgress();
        mView.updateStoreInfoChart(pieData);
    }
}
