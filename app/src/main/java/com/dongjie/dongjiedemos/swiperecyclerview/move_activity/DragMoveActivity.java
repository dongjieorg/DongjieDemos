package com.dongjie.dongjiedemos.swiperecyclerview.move_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

public class DragMoveActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_move);
    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, DragSwipListActivity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {
        Intent it = new Intent(this, DragGridActivity.class);
        startActivity(it);
    }

    public void onButtonClick3(View v) {
        Intent it = new Intent(this, DragSwipListDefineActivity.class);
        startActivity(it);
    }

    public void onButtonClick4(View v) {
        Intent it = new Intent(this, DragSwipGridDefineActivity.class);
        startActivity(it);
    }
}
