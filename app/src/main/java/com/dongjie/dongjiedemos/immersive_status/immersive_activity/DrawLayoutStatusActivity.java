package com.dongjie.dongjiedemos.immersive_status.immersive_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.jaeger.library.StatusBarUtil;

public class DrawLayoutStatusActivity extends BaseActivity {
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_layout_status);
        mDrawerLayout = findViewById( R.id.drawer_layout );
        StatusBarUtil.setColorForDrawerLayout( this  , mDrawerLayout , Color.BLUE);
    }
}
