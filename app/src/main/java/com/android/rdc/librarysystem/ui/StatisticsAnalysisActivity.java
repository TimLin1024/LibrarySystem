package com.android.rdc.librarysystem.ui;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.contract.StatisticContract;
import com.android.rdc.librarysystem.presenter.StatisticPresenter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;

import butterknife.BindView;

/**
 * 图书统计分析
 * 图书类型比例  图书表中 group by BookType
 * 库存比例 图书表中 group by isBorrow
 */
public class StatisticsAnalysisActivity extends BaseToolbarActivity implements StatisticContract.View {

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
        Description description = new Description();
        description.setText("图书类型馆藏图");
        mPieChartStoreInfo.setDescription(description);
        mPieChartBookType.setHoleRadius(40f);//中间空白圆的半径
        mPieChartBookType.setTransparentCircleRadius(46f);//透明圆的半径

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
        Description description = new Description();
        description.setText("图书在馆/出借图");
        mPieChartStoreInfo.setDescription(description);
        mPieChartStoreInfo.setHoleRadius(40f);
        mPieChartStoreInfo.setTransparentCircleRadius(46f);

        Legend legend = mPieChartStoreInfo.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
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
