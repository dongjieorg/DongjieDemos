package com.dongjie.dongjiedemos.viewpager.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.viewpager.adapter.MyFragmentPagerAdapter;
import com.dongjie.dongjiedemos.viewpager.custom.VerticalViewPager;

public class VerticalPagerActivity extends BaseActivity {

    VerticalViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_pager);

        mViewPager = findViewById(com.joybar.librarycalendar.R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setVertical(true);
        // ViewPager左右滑动的监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
    }
}
