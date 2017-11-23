package com.android.rdc.librarysystem.model;

import com.android.rdc.librarysystem.R;
import com.android.rdc.librarysystem.bean.HomeItemBean;

import java.util.ArrayList;
import java.util.List;

public class HomeModel {

    public static List<HomeItemBean> generateHomeItem() {
        List<HomeItemBean> homeItemBeans = new ArrayList<>();
        HomeItemBean readerItemBean = new HomeItemBean();
        readerItemBean.setIconId(R.drawable.ic_reader);
        readerItemBean.setItemName("添加读者");
        homeItemBeans.add(readerItemBean);

        HomeItemBean bookItemBean = new HomeItemBean();
        bookItemBean.setIconId(R.drawable.ic_book);
        bookItemBean.setItemName("添加书籍");
        homeItemBeans.add(bookItemBean);

        return homeItemBeans;
    }

}
