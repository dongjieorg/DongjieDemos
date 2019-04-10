package com.dongjie.dongjiedemos.swiperecyclerview.group_activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

/*
 * 注意：
 * 1. 要给需要sticky的View设置tab属性：android:tag="sticky";
 * 2. 也可以Java动态设置：view.setTag("sticky");
 * 3. 如果这个sticky的View是可点击的，那么tag为：android:tag="sticky-nonconstant"或者view.setTag("sticky-nonconstant");
 */

public class GroupLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_layout);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabLayout.Tab tab = tabLayout.newTab();
        tab.setText("商品预览");
        tabLayout.addTab(tab);

        tab = tabLayout.newTab();
        tab.setText("商品详情");
        tabLayout.addTab(tab);

        tab = tabLayout.newTab();
        tab.setText("商品描述");
        tabLayout.addTab(tab);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(GroupLayoutActivity.this, "第" + tab.getPosition() + "个Tab", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
