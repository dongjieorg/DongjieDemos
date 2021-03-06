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
import com.dongjie.dongjiedemos.andpermission.PermissionActivity;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.big_image.BigImageActivity;
import com.dongjie.dongjiedemos.calendar.CalendarActivity;
import com.dongjie.dongjiedemos.flow_layout.FlowLayoutActivity;
import com.dongjie.dongjiedemos.fragment_stack.FragmentStackManagerActivity;
import com.dongjie.dongjiedemos.gaode_map.GaodeActivity;
import com.dongjie.dongjiedemos.gpu_image.GPUImageActivity;
import com.dongjie.dongjiedemos.immersive_status.ImmersiveActivity;
import com.dongjie.dongjiedemos.lame_record.RecordLameActivity;
import com.dongjie.dongjiedemos.notification.NotificationActivity;
import com.dongjie.dongjiedemos.paging.PagingActivity;
import com.dongjie.dongjiedemos.pull_refresh.PullRefreshActivity;
import com.dongjie.dongjiedemos.rxjava.RxJavaDemoActivity;
import com.dongjie.dongjiedemos.rxjava.rxjava.RxJavaActivity;
import com.dongjie.dongjiedemos.share_element.ShareElement1Activity;
import com.dongjie.dongjiedemos.swiperecyclerview.SwipeRecyclerViewActivity;
import com.dongjie.dongjiedemos.tab_indicator.TabIndicatorActivitys;
import com.dongjie.dongjiedemos.tablayout.TabLayoutActivity;
import com.dongjie.dongjiedemos.touch_finish_activity.TouchFinishActivity;
import com.dongjie.dongjiedemos.videoplayer.VideoPlayerActivity;
import com.dongjie.dongjiedemos.viewpager.PagerActivity;
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

        // 第三方TabLayout demo
        classBean = new ClassBean();
        classBean.setTitle("第三方TabLayout的demo");
        classBean.setClassName(TabLayoutActivity.class);
        list.add(classBean);

        // 第三方TabLayout demo
        classBean = new ClassBean();
        classBean.setTitle("权限封装");
        classBean.setClassName(PermissionActivity.class);
        list.add(classBean);

        // SwipeRecyclerView
        classBean = new ClassBean();
        classBean.setTitle("SwipeRecyclerView");
        classBean.setClassName(SwipeRecyclerViewActivity.class);
        list.add(classBean);

        // Paging
        classBean = new ClassBean();
        classBean.setTitle("谷歌第三方分页加载库Paging");
        classBean.setClassName(PagingActivity.class);
        list.add(classBean);

        // 自己写的fragment栈管理
        classBean = new ClassBean();
        classBean.setTitle("仿Activity的fragment栈管理");
        classBean.setClassName(FragmentStackManagerActivity.class);
        list.add(classBean);

        // 日历demo
        classBean = new ClassBean();
        classBean.setTitle("日历demo");
        classBean.setClassName(CalendarActivity.class);
        list.add(classBean);

        // 动态加载Fragment的ViewPager
        classBean = new ClassBean();
        classBean.setTitle("ViewPager动态加载fragment");
        classBean.setClassName(PagerActivity.class);
        list.add(classBean);

        // 视频播放器
        classBean = new ClassBean();
        classBean.setTitle("视频播放器");
        classBean.setClassName(VideoPlayerActivity.class);
        list.add(classBean);

        // 下拉刷新和上拉加载更多的lib
        classBean = new ClassBean();
        classBean.setTitle("下拉刷新和上拉加载更多");
        classBean.setClassName(PullRefreshActivity.class);
        list.add(classBean);

        // 流式布局
        classBean = new ClassBean();
        classBean.setTitle("流式布局");
        classBean.setClassName(FlowLayoutActivity.class);
        list.add(classBean);

        // 共享元素动画
        classBean = new ClassBean();
        classBean.setTitle("共享元素动画");
        classBean.setClassName(ShareElement1Activity.class);
        list.add(classBean);

        // GPUImage
        classBean = new ClassBean();
        classBean.setTitle("GPUImage");
        classBean.setClassName(GPUImageActivity.class);
        list.add(classBean);

        // RxJava
        classBean = new ClassBean();
        classBean.setTitle("RxJava");
        classBean.setClassName(RxJavaDemoActivity.class);
        list.add(classBean);

        // 大图显示
        classBean = new ClassBean();
        classBean.setTitle("显示大图");
        classBean.setClassName(BigImageActivity.class);
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
