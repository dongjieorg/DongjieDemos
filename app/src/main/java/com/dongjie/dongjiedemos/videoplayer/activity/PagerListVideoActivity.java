package com.dongjie.dongjiedemos.videoplayer.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.videoplayer.adapter.VideoListAdapter;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class PagerListVideoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_list_video);

        List<View> listViews = new ArrayList<>();
        ListView listView1 = (ListView) getLayoutInflater().inflate(R.layout.layout_list, null);
        ListView listView2 = (ListView) getLayoutInflater().inflate(R.layout.layout_list, null);
        ListView listView3 = (ListView) getLayoutInflater().inflate(R.layout.layout_list, null);

        listView1.setAdapter(new VideoListAdapter(this));
        listView2.setAdapter(new VideoListAdapter(this));
        listView3.setAdapter(new VideoListAdapter(this));

        listViews.add(listView1);
        listViews.add(listView2);
        listViews.add(listView3);

        MyAdapter myAdapter = new MyAdapter(listViews);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(myAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                JCVideoPlayer.releaseAllVideos();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    public class MyAdapter extends PagerAdapter {

        List<View> viewLists;

        public MyAdapter(List<View> lists) {
            viewLists = lists;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View view, int position, Object object) {
            ((ViewPager) view).removeView(viewLists.get(position));
        }

        @Override
        public Object instantiateItem(View view, int position) {
            ((ViewPager) view).addView(viewLists.get(position), 0);
            return viewLists.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
