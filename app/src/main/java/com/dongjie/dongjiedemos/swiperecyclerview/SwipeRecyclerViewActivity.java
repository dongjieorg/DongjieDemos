package com.dongjie.dongjiedemos.swiperecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.expend_activity.ExpandableActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.group_activity.SwipeGroupActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.header_footer_activity.RecyclerHeaderFooterActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.menu_activity.MenuActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.move_activity.DragMoveActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.pullrefresh_load_activity.RefreshLoadMoreActivity;

/**
 * GitHub地址：https://github.com/yanzhenjie/SwipeRecyclerView
 */
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
        Intent it = new Intent(this, DragMoveActivity.class);
        startActivity(it);
    }

    public void onButtonClick3(View v) {
        Intent it = new Intent(this, RecyclerHeaderFooterActivity.class);
        startActivity(it);
    }

    public void onButtonClick4(View v) {
        Intent it = new Intent(this, RefreshLoadMoreActivity.class);
        startActivity(it);
    }

    public void onButtonClick5(View v) {
        Intent it = new Intent(this, ExpandableActivity.class);
        startActivity(it);
    }

    public void onButtonClick6(View v) {
        Intent it = new Intent(this, SwipeGroupActivity.class);
        startActivity(it);
    }
}
