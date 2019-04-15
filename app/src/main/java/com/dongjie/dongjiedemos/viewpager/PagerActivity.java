package com.dongjie.dongjiedemos.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.viewpager.activity.DynamicViewPagerActivity;
import com.dongjie.dongjiedemos.viewpager.activity.VerticalPagerActivity;

public class PagerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, DynamicViewPagerActivity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {
        Intent it = new Intent(this, VerticalPagerActivity.class);
        startActivity(it);
    }
}
