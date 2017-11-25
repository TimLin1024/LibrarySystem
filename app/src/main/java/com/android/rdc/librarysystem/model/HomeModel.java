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

        HomeItemBean readerTypeItemBean = new HomeItemBean();
        readerTypeItemBean.setIconId(R.drawable.ic_reader);
        readerTypeItemBean.setItemName("添加读者类型");
        homeItemBeans.add(readerTypeItemBean);

        HomeItemBean bookItemBean = new HomeItemBean();
        bookItemBean.setIconId(R.drawable.ic_book);
        bookItemBean.setItemName("添加书籍");
        homeItemBeans.add(bookItemBean);

        HomeItemBean bookTypeItemBean = new HomeItemBean();
        bookTypeItemBean.setIconId(R.drawable.ic_book);
        bookTypeItemBean.setItemName("添加书籍类型");
        homeItemBeans.add(bookTypeItemBean);

        HomeItemBean borrowBookItemBean = new HomeItemBean();
        borrowBookItemBean.setIconId(R.drawable.ic_borrow_book);
        borrowBookItemBean.setItemName("借书");
        homeItemBeans.add(borrowBookItemBean);

        homeItemBeans.add(new HomeItemBean("还书", R.drawable.ic_book));

        homeItemBeans.add(new HomeItemBean("查看书籍列表", R.drawable.ic_book));
        return homeItemBeans;
    }

}
