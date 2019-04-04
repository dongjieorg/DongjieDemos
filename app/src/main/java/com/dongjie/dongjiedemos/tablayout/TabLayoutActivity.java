package com.dongjie.dongjiedemos.tablayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.tablayout.activitys.CommonTabActivity;
import com.dongjie.dongjiedemos.tablayout.activitys.SegmentTabActivity;
import com.dongjie.dongjiedemos.tablayout.activitys.SlidingTabActivity;

/**
 * 第三方的TabLayout，功能齐全
 * github地址: 
 */
public class TabLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, SlidingTabActivity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {
        Intent it = new Intent(this, CommonTabActivity.class);
        startActivity(it);
    }

    public void onButtonClick3(View v) {
        Intent it = new Intent(this, SegmentTabActivity.class);
        startActivity(it);
    }
}
