package com.dongjie.dongjiedemos.swiperecyclerview.header_footer_activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.adapter.MainAdapter;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * ListView形式的侧滑菜单，支持菜单横向和竖向的。
 */
public class RecyclerHeaderFooterActivity extends BaseActivity {
    SwipeRecyclerView mRecyclerView;
    MainAdapter mAdapter;
    List<String> mDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mDataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDataList.add("第" + i + "个Item");
        }

        // HeaderView。
        View headerView = getLayoutInflater().inflate(R.layout.layout_header, mRecyclerView, false);
        headerView.findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "HeaderView", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.addHeaderView(headerView);

        // FooterView。
        View footerView = getLayoutInflater().inflate(R.layout.layout_footer, mRecyclerView, false);
        footerView.findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "FooterView", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.addFooterView(footerView);


        mAdapter = new MainAdapter(this, mDataList);
        mRecyclerView.setAdapter(mAdapter);
    }
}