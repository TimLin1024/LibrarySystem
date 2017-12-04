package com.android.rdc.librarysystem;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.rdc.amdroidutil.base.BaseToolbarActivity;
import com.android.rdc.amdroidutil.listener.OnClickRecyclerViewListener;
import com.android.rdc.librarysystem.adapter.GridRvAdapter;
import com.android.rdc.librarysystem.model.HomeModel;
import com.android.rdc.librarysystem.ui.AddBookActivity;
import com.android.rdc.librarysystem.ui.AddBookTypeActivity;
import com.android.rdc.librarysystem.ui.AddReaderActivity;
import com.android.rdc.librarysystem.ui.AddReaderTypeActivity;
import com.android.rdc.librarysystem.ui.BorrowBookActivity;
import com.android.rdc.librarysystem.ui.QueryBookActivity;
import com.android.rdc.librarysystem.ui.QueryReaderActivity;
import com.android.rdc.librarysystem.ui.ReturnBookActivity;
import com.android.rdc.librarysystem.ui.StatisticsAnalysisActivity;
import com.android.rdc.librarysystem.ui.TypeInfoActivity;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import butterknife.BindView;

public class MainActivity extends BaseToolbarActivity {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.boom_menu_btn)
    BoomMenuButton mBoomMenuButton;
    private GridRvAdapter mAdapter;


    private String[] mBtnText = new String[]{"添加读者", "添加读者类型", "添加书籍", "添加书籍类型"};
    private int[][] mSubBtnColorList = {
            {
                    R.color.colorPrimary, R.color.colorPrimaryDark
            },
            {
                    R.color.colorPrimary, R.color.colorPrimaryDark
            },
            {
                    R.color.colorPrimary, R.color.colorPrimaryDark
            },
            {
                    R.color.colorPrimary, R.color.colorPrimaryDark
            }
    };


    @Override
    protected int setLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        setTitle("图书管理系统");
    }

    @Override
    protected void initView() {
        mAdapter = new GridRvAdapter();
        mAdapter.appendData(HomeModel.generateHomeItem());
        mRv.setAdapter(mAdapter);
        mRv.setLayoutManager(new GridLayoutManager(this, 3));
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

//        initSatelliteMenu();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Drawable[] mDrawables = new Drawable[]{getDrawable(R.drawable.ic_add),
                getDrawable(R.drawable.ic_add_reader),
                getDrawable(R.drawable.ic_add),
                getDrawable(R.drawable.ic_book)};

        mBoomMenuButton.init(
                mDrawables,
                mBtnText,
                mSubBtnColorList,
                ButtonType.HAM,
                BoomType.LINE,
                PlaceType.HAM_4_1,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        mBoomMenuButton.setOnSubButtonClickListener(buttonIndex -> {
            switch (buttonIndex) {
                case 0:
                    startActivity(AddReaderActivity.class);
                    break;
                case 1:
                    startActivity(AddReaderTypeActivity.class);
                    break;
                case 2:
                    startActivity(AddBookActivity.class);
                    break;
                case 3:
                    startActivity(AddBookTypeActivity.class);
                    break;
            }
        });
    }

    private void initSatelliteMenu() {

        ImageView ivAddReader = new ImageView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(40, 40);
        ivAddReader.setLayoutParams(layoutParams);
        ivAddReader.setImageResource(R.drawable.ic_add_reader);
        ivAddReader.setBackground(getResources().getDrawable(R.drawable.circle_white));
        ivAddReader.setOnClickListener(v -> {
            showToast("点击了添加读者");
        });

        ImageView ivAddBook = new ImageView(this);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(40, 40);
        ivAddBook.setLayoutParams(layoutParams);
        ivAddBook.setImageResource(R.drawable.ic_book);
        ivAddBook.setBackground(getResources().getDrawable(R.drawable.circle_white));
        ivAddBook.setOnClickListener(v -> {
            showToast("点击了添加");
        });

        SubActionButton.Builder itemAddReaderBuilder = new SubActionButton.Builder(this)
                .setContentView(ivAddReader);
        SubActionButton.Builder itemAddBookBuilder = new SubActionButton.Builder(this)
                .setContentView(ivAddBook);

//        FloatingActionMenu floatingActionMenu = new FloatingActionMenu.Builder(this)
//                .addSubActionView(itemAddReaderBuilder.build())
//                .addSubActionView(itemAddBookBuilder.build())
//                .attachTo(mFloatingActionButton)
//                .build();
    }

    @Override
    protected void initListener() {
        mAdapter.setOnRecyclerViewListener(new OnClickRecyclerViewListener() {
            //读者 读者类型 书籍 书籍类型
            @Override
            public void onItemClick(int i) {
                switch (i) {
                    case 0:
                        startActivity(AddReaderActivity.class);
                        break;
                    case 1:
                        startActivity(AddReaderTypeActivity.class);
                        break;
                    case 2:
                        startActivity(AddBookActivity.class);
                        break;
                    case 3:
                        startActivity(AddBookTypeActivity.class);
                        break;
                    case 4:
                        startActivity(BorrowBookActivity.class);
                        break;
                    case 5:
                        startActivity(ReturnBookActivity.class);
                        break;
                    case 6:
                        startActivity(QueryBookActivity.class);
                        break;
                    case 7:
                        startActivity(QueryReaderActivity.class);
                        break;
                    case 8:
                        startActivity(StatisticsAnalysisActivity.class);
                        break;
                    case 9:
                        startActivity(TypeInfoActivity.class);
                        break;

                    default:
                }
            }

            @Override
            public boolean onItemLongClick(int i) {
                return false;
            }
        });
    }

}
