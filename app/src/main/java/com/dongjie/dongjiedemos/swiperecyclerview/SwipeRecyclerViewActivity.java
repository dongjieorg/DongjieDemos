package com.dongjie.dongjiedemos.swiperecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.menu_activity.MenuActivity;

public class SwipeRecyclerViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_recycler_view);
    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, MenuActivity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {

    }

    public void onButtonClick3(View v) {

    }

    public void onButtonClick4(View v) {

    }

    public void onButtonClick5(View v) {

    }

    public void onButtonClick6(View v) {

    }

    public void onButtonClick7(View v) {

    }
}
