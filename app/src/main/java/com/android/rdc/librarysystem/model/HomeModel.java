package com.android.rdc.librarysystem.model;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.bean.HomeItemBean;

import java.util.ArrayList;
import java.util.List;

public class HomeModel {

    public static List<HomeItemBean> generateHomeItem() {
        List<HomeItemBean> homeItemBeans = new ArrayList<>();
        homeItemBeans.add(new HomeItemBean("添加读者", R.drawable.ic_reader));
        homeItemBeans.add(new HomeItemBean("添加读者类型", R.drawable.ic_reader));
        homeItemBeans.add(new HomeItemBean("添加书籍", R.drawable.ic_book));
        homeItemBeans.add(new HomeItemBean("添加书籍类型", R.drawable.ic_book));
        homeItemBeans.add(new HomeItemBean("借书", R.drawable.ic_borrow_book));
        homeItemBeans.add(new HomeItemBean("还书", R.drawable.ic_book));
        homeItemBeans.add(new HomeItemBean("查看书籍列表", R.drawable.ic_book));
        homeItemBeans.add(new HomeItemBean("查看读者列表", R.drawable.ic_reader));
        homeItemBeans.add(new HomeItemBean("统计分析", R.drawable.ic_statiscs));
        return homeItemBeans;
    }

}
