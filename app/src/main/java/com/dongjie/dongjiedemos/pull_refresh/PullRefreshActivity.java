package com.dongjie.dongjiedemos.pull_refresh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.pull_refresh.activity.BezierRadarHeaderActivity;
import com.dongjie.dongjiedemos.pull_refresh.activity.ClassicsHeaderActivity;
import com.dongjie.dongjiedemos.pull_refresh.activity.ViewRefreshLoadMoreActivity;

/**
 * GitHub地址：https://github.com/scwang90/SmartRefreshLayout
 * 默认只支持两种：ClassicsHeader和BezierRadarHeader两种样式， 如果要支持更多炫酷效果，需要引入另外的header和footer包
 * 自定义Header和footer:     https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_custom.md
 */
public class PullRefreshActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_refresh);
    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, BezierRadarHeaderActivity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {
        Intent it = new Intent(this, ClassicsHeaderActivity.class);
        startActivity(it);
    }

    public void onButtonClick3(View v) {
        Intent it = new Intent(this, ViewRefreshLoadMoreActivity.class);
        startActivity(it);
    }
}
