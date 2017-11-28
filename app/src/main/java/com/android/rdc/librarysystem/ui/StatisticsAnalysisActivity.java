package com.android.rdc.librarysystem.ui;

import android.database.Cursor;
import android.widget.TextView;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.bean.BookType;
import com.android.rdc.librarysystem.bean.BookTypeStatisticBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StatisticsAnalysisActivity extends BaseToolbarActivity {


    @BindView(R.id.tv_msg)
    TextView mTvMsg;

    //图书类型比例  图书表中 group by BookType
    //库存比例 图书表中 group by isBorrow
    @Override
    protected int setLayoutResID() {
        return R.layout.activity_statistics_analysis;
    }

    @Override
    protected void initData() {
        getBookTypeStatistic();
        getStorageStatistic();
    }

    private void getStorageStatistic() {
        Cursor cursor = DataSupport.findBySQL("select count(id) ,isborrowed  是否出借 from book group by isborrowed");
        // TODO: 2017/11/27 0027 区分出借与否
//        cus
    }

    private void getBookTypeStatistic() {
        //执行 select 的结果是 List ，无法进行 count？
        Cursor cursor = DataSupport.findBySQL("select booktype_id,count(id)" +
                "from book " +
                "group by booktype_id");
        //获取类型名字，展示类型数量
        List<BookTypeStatisticBean> bookTypeStatisticBeanList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            bookTypeStatisticBeanList.add(getStatisticBean(cursor));
            while (cursor.moveToNext()) {
                bookTypeStatisticBeanList.add(getStatisticBean(cursor));
            }
        }
        cursor.close();

        StringBuilder stringBuilder = new StringBuilder("比例信息：\n");
        for (BookTypeStatisticBean bean : bookTypeStatisticBeanList) {
            stringBuilder.append(bean.toString()).append("\n");
        }
        mTvMsg.setText(stringBuilder.toString());
    }

    private BookTypeStatisticBean getStatisticBean(Cursor cursor) {
        BookTypeStatisticBean bean = new BookTypeStatisticBean();
        bean.setBookTypeId(cursor.getLong(0));//书籍类型 id
        bean.setCount(cursor.getInt(1));//数量
        bean.setBookTypeName(DataSupport.find(BookType.class, bean.getBookTypeId()).getTypeName());//类型名
        return bean;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

}
