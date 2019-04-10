package com.dongjie.dongjiedemos.swiperecyclerview.group_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.menu_activity.MenuActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.move_activity.DragMoveActivity;

public class SwipeGroupActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_group);
    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, GroupLayoutActivity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {
        Intent it = new Intent(this, GroupMenuActivity.class);
        startActivity(it);
    }
}
