package com.dongjie.dongjiedemos.immersive_status.immersive_activity;

import android.os.Bundle;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.jaeger.library.StatusBarUtil;

public class FullScreenStatusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_status);
        StatusBarUtil.setTransparent(this) ;
    }
}
