package com.dongjie.dongjiedemos.immersive_status;

import android.os.Bundle;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

/**
 * 第三方沉浸式状态栏
 * GitHub地址：https://github.com/gyf-dev/ImmersionBar
 */
public class ImmersiveActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersive);
    }
}
