package com.dongjie.dongjiedemos.touch_finish_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

/**
 * 使用文档参考：https://blog.csdn.net/xiaanming/article/details/20934541
 */

public class TouchFinishActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_finish);
    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, NormalActivity.class);
        startActivity(it);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    public void onButtonClick2(View v) {
        Intent it = new Intent(this, ListViewActivity.class);
        startActivity(it);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    public void onButtonClick3(View v) {
        Intent it = new Intent(this, ScrollActivity.class);
        startActivity(it);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    public void onButtonClick4(View v) {
        Intent it = new Intent(this, RecyclerActivity.class);
        startActivity(it);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    public void onButtonClick5(View v) {
        Intent it = new Intent(this, ViewPagerActivity.class);
        startActivity(it);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }
}
