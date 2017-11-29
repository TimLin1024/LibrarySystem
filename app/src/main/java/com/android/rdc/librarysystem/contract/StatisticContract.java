package com.android.rdc.librarysystem.contract;


import com.github.mikephil.charting.data.PieData;

public interface StatisticContract {
    //最终目的是通过图表的形式展示出来，因此数据类型可以选择为 PieData 或者 PieEntity
    interface Model {
        void getBookTypeData(OnStatisticFetchListener listener);

        void getStoreInfoData(OnStatisticFetchListener listener);

        interface OnStatisticFetchListener {
            void onFetchBookTypeData(PieData items);

            void onFetchStoreInfoData(PieData items);
        }
    }

    interface View {
        void updateBookTypeChart(PieData pieDataList);

        void updateStoreInfoChart(PieData pieDataList);

        void showProgress();

        void hideProgress();
    }

    interface Presenter {

        void onResume();

        void onDestroy();
    }
}