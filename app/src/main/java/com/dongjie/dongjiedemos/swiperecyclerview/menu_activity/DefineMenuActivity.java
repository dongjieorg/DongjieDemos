package com.dongjie.dongjiedemos.swiperecyclerview.menu_activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.adapter.DefineAdapter;
import com.dongjie.dongjiedemos.swiperecyclerview.adapter.MainAdapter;
import com.dongjie.dongjiedemos.tools.ToastUtils;
import com.yanzhenjie.recyclerview.SwipeMenuLayout;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义侧滑菜单
 */
public class DefineMenuActivity extends BaseActivity {
    SwipeRecyclerView mRecyclerView;
    DefineAdapter mAdapter;
    List<String> mDataList;
    SwipeMenuLayout mSwipeMenuLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_define);
        mRecyclerView = findViewById(R.id.recycler_view);
        mSwipeMenuLayout = findViewById(R.id.swipe_layout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mDataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mDataList.add("第" + i + "个Item");
        }

        mAdapter = new DefineAdapter(this, mDataList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onLeftMenuBtClick(View v) {
        mSwipeMenuLayout.smoothCloseLeftMenu();
        ToastUtils.showToast(this, "你点击了左侧菜单按钮");
    }

    public void onLeftMenuBt2Click(View v) {
        mSwipeMenuLayout.smoothCloseLeftMenu();
        ToastUtils.showToast(this, "你点击了左侧菜单按钮2");
    }

    public void onRightMenuBtClick(View v) {
        mSwipeMenuLayout.smoothCloseRightMenu();
        ToastUtils.showToast(this, "你点击了右侧菜单按钮");
    }
}