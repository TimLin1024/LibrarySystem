package com.android.rdc.librarysystem.model;

import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.android.rdc.librarysystem.bean.BookType;
import com.android.rdc.librarysystem.contract.StatisticContract;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class StatisticModel implements StatisticContract.Model {
    @Override
    public void getBookTypeData(OnStatisticFetchListener listener) {

        Cursor cursor = DataSupport.findBySQL("select booktype_id,count(id)" +
                "from book " +
                "group by booktype_id");
        //获取类型名字，展示类型数量
        List<PieEntry> pieEntries = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            PieEntry pieEntry = getBookTypePieEntry(cursor);
            pieEntries.add(pieEntry);
            while (cursor.moveToNext()) {
                PieEntry pieEntry1 = getBookTypePieEntry(cursor);
                pieEntries.add(pieEntry1);
            }
            cursor.close();
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "图书类型");//数据集
        pieDataSet.setLabel("");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);//颜色集
        pieDataSet.setSliceSpace(2f);//片与片的间隔
        pieDataSet.setValueTextColor(Color.WHITE);//饼状图字体颜色
        pieDataSet.setValueTextSize(12f);//饼状图字体大小

        PieData pieData = new PieData(pieDataSet);
        listener.onFetchBookTypeData(pieData);
    }

    @NonNull
    private PieEntry getBookTypePieEntry(Cursor cursor) {
        return new PieEntry(cursor.getInt(1),
                DataSupport.find(BookType.class, cursor.getLong(0)).getTypeName());
    }

    @Override
    public void getStoreInfoData(OnStatisticFetchListener listener) {
        Cursor cursor = DataSupport.findBySQL("select isborrowed,count(id) 是否出借 from book group by isborrowed");

        //获取类型名字，展示类型数量
        List<PieEntry> pieEntries = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            PieEntry pieEntry = getPieEntry(cursor);
            pieEntries.add(pieEntry);
            while (cursor.moveToNext()) {
                PieEntry pieEntry1 = getPieEntry(cursor);
                pieEntries.add(pieEntry1);
            }
            cursor.close();
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "图书状态");//数据集
        pieDataSet.setLabel("");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);//颜色集
        pieDataSet.setSliceSpace(2f);//片与片的间隔
        pieDataSet.setValueTextColor(Color.WHITE);//饼状图字体颜色
        pieDataSet.setValueTextSize(12f);//饼状图字体大小
        PieData pieData = new PieData(pieDataSet);
        listener.onFetchStoreInfoData(pieData);
    }

    @NonNull
    private PieEntry getPieEntry(Cursor cursor) {
        return new PieEntry(cursor.getInt(1),
                cursor.getInt(0) == 1 ? "借出" : "在馆");
    }

}
