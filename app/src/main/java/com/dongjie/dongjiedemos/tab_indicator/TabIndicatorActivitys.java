package com.dongjie.dongjiedemos.tab_indicator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.tab_indicator.activitys.TabIndicatorActivity;
import com.dongjie.dongjiedemos.tab_indicator.activitys.TabPageIndicatorActivity;
import com.dongjie.dongjiedemos.tab_indicator.activitys.TabPageIndicatorExActivity;

/**
 * github：https://github.com/xuehuayous/Android-TabIndicator
 */
public class TabIndicatorActivitys extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_indicators);
    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, TabPageIndicatorExActivity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {
        Intent it = new Intent(this, TabPageIndicatorActivity.class);
        startActivity(it);
    }

    public void onButtonClick3(View v) {
        Intent it = new Intent(this, TabIndicatorActivity.class);
        startActivity(it);
    }
}
