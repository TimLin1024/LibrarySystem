package com.android.rdc.librarysystem.ui;

import android.widget.TextView;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.contract.StatisticContract;
import com.android.rdc.librarysystem.presenter.StatisticPresenter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;

import butterknife.BindView;

/**
 * 图书统计分析
 * 图书类型比例  图书表中 group by BookType
 * 库存比例 图书表中 group by isBorrow
 */
public class StatisticsAnalysisActivity extends BaseToolbarActivity implements StatisticContract.View {

    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    @BindView(R.id.pie_chart_book_type)
    PieChart mPieChartBookType;
    @BindView(R.id.pie_chart_store_info)
    PieChart mPieChartStoreInfo;

    private StatisticContract.Presenter mPresenter;

    @Override
    protected int setLayoutResID() {
        return R.layout.activity_statistics_analysis;
    }

    @Override
    protected void initData() {
        mPresenter = new StatisticPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();//默认会去获取数据
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();//默认实现会解除绑定
        super.onDestroy();
    }


    @Override
    protected void initView() {
        setTitle("统计分析");
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void updateBookTypeChart(PieData pieData) {
        mPieChartBookType.getDescription().setEnabled(true);
//        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
//        mPieChartBookType.setCenterTextTypeface(tf);
//        mPieChartBookType.setCenterText(generateCenterText());
//        mPieChartBookType.setCenterTextSize(10f);
//        mPieChartBookType.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        mPieChartBookType.setHoleRadius(45f);//中间空白圆的半径
        mPieChartBookType.setTransparentCircleRadius(50f);//透明圆的半径

        Legend legend = mPieChartBookType.getLegend();//获取图标的图例
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//设置图例的垂直对齐
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);//设置图例的水平对齐
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);//设置图例的方向
        legend.setDrawInside(false);
        mPieChartBookType.setData(pieData);
    }

    @Override
    public void updateStoreInfoChart(PieData pieData) {
        mPieChartStoreInfo.getDescription().setEnabled(true);
        mPieChartStoreInfo.setHoleRadius(45f);
        mPieChartStoreInfo.setTransparentCircleRadius(47f);

        Legend legend = mPieChartStoreInfo.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(true);
        mPieChartStoreInfo.setData(pieData);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}
