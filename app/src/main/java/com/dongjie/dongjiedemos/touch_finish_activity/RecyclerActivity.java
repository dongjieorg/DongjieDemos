package com.dongjie.dongjiedemos.touch_finish_activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.touch_finish_activity.touch_finish_lib.SwipeBackActivity;

import java.util.ArrayList;

public class RecyclerActivity extends SwipeBackActivity {

    RecyclerView mRecyclerView;
    ArrayList<String> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mRecyclerView = findViewById(R.id.recycler_view);

        mList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mList.add("测试数据" + i);
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        mRecyclerView.setAdapter(adapter);
        //设置增加或删除条目的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(RecyclerActivity.this).inflate(R.layout.listitem_layout, null);
            return new MyHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
            myHolder.textView.setText(mList.get(i));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView textView;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.item_text);
            }
        }
    }
}
