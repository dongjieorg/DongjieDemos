package com.dongjie.dongjiedemos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dongjie.dongjiedemos.album.AlbumActivity;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.gaode_map.GaodeActivity;
import com.dongjie.dongjiedemos.immersive_status.ImmersiveActivity;
import com.dongjie.dongjiedemos.lame_record.RecordLameActivity;
import com.dongjie.dongjiedemos.notification.NotificationActivity;
import com.dongjie.dongjiedemos.tab_indicator.TabIndicatorActivitys;
import com.dongjie.dongjiedemos.touch_finish_activity.TouchFinishActivity;
import com.dongjie.dongjiedemos.vlayout.VLayoutActivity;
import com.dongjie.dongjiedemos.zxing.ZXingActivity;
import com.jaeger.library.StatusBarUtil;

import java.util.LinkedList;

public class MainActivity extends BaseActivity {
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        mRecyclerView = findViewById(R.id.recycleview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinkedList<ClassBean> list = new LinkedList<>();
        ClassBean classBean;

        classBean = new ClassBean();
        classBean.setTitle("v-layout Demo");
        classBean.setClassName(VLayoutActivity.class);
        list.add(classBean);

        // 第三方的通知，兼容到8.0
        classBean = new ClassBean();
        classBean.setTitle("第三方的通知，兼容到8.0");
        classBean.setClassName(NotificationActivity.class);
        list.add(classBean);

        // 录音， 保存为mp3格式
        classBean = new ClassBean();
        classBean.setTitle("录音，保存成mp3格式（lame三方库）");
        classBean.setClassName(RecordLameActivity.class);
        list.add(classBean);

        // 第三方相册
        classBean = new ClassBean();
        classBean.setTitle("相册");
        classBean.setClassName(AlbumActivity.class);
        list.add(classBean);

        // 第三方状态栏 沉浸式
        classBean = new ClassBean();
        classBean.setTitle("沉浸式");
        classBean.setClassName(ImmersiveActivity.class);
        list.add(classBean);

        // Zxing
        classBean = new ClassBean();
        classBean.setTitle("Zxing");
        classBean.setClassName(ZXingActivity.class);
        list.add(classBean);

        // 侧滑finish activity
        classBean = new ClassBean();
        classBean.setTitle("侧滑finish activity");
        classBean.setClassName(TouchFinishActivity.class);
        list.add(classBean);

        // 高德地图demo
        classBean = new ClassBean();
        classBean.setTitle("高德地图demo");
        classBean.setClassName(GaodeActivity.class);
        list.add(classBean);

        // Tab Indicator demo
        classBean = new ClassBean();
        classBean.setTitle("TabIndicator的demo");
        classBean.setClassName(TabIndicatorActivitys.class);
        list.add(classBean);

        MyAdapter myAdapter = new MyAdapter(this, list);
        mRecyclerView.setAdapter(myAdapter);
    }

    class MyAdapter extends RecyclerView.Adapter {
        private Context context;
        private View view;
        private LinkedList<ClassBean> list;

        MyAdapter(Context context, LinkedList<ClassBean> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_demo_show_item_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ClassBean myBean = list.get(position);
            ((MyHolder) holder).myTextView.setText(myBean.getTitle());
            ((MyHolder) holder).myRel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent mIntent = new Intent();
                    mIntent.setClass(context, myBean.getClassName());
                    startActivity(mIntent);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (list == null) {
                return 0;
            } else {
                return list.size();
            }
        }

        class MyHolder extends RecyclerView.ViewHolder {
            private TextView myTextView;
            private RelativeLayout myRel;

            MyHolder(View itemView) {
                super(itemView);
                myRel = view.findViewById(R.id.desk_adapter_rel);
                myTextView = view.findViewById(R.id.desk_adapter_txt_id);
            }
        }
    }

    public class ClassBean<T> {
        private String title;
        private Class<T> className;

        String getTitle() {
            return title;
        }

        void setTitle(String title) {
            this.title = title;
        }

        Class<T> getClassName() {
            return className;
        }

        void setClassName(Class<T> className) {
            this.className = className;
        }
    }
}
