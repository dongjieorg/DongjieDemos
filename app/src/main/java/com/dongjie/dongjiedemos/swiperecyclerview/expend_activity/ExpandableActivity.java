package com.dongjie.dongjiedemos.swiperecyclerview.expend_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

public class ExpandableActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, ExpandListActivity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {
        Intent it = new Intent(this, ExpandGridActivity.class);
        startActivity(it);
    }

    public void onButtonClick3(View v) {
        Intent it = new Intent(this, ExpandStaggeredActivity.class);
        startActivity(it);
    }
}
