package com.dongjie.dongjiedemos.immersive_status;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.immersive_status.immersive_activity.DrawLayoutStatusActivity;
import com.dongjie.dongjiedemos.immersive_status.immersive_activity.FullScreenStatusActivity;
import com.dongjie.dongjiedemos.immersive_status.immersive_activity.StatusColorActivity;

/**
 * 第三方沉浸式状态栏
 * GitHub地址：https://github.com/zyj1609wz/Android-StatusBarColor
 */
public class ImmersiveActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersive);
    }

    public void onButtonClick(View v) {
        Intent it = new Intent(this, StatusColorActivity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {
        Intent it = new Intent(this, FullScreenStatusActivity.class);
        startActivity(it);
    }

    public void onButtonClick3(View v) {
        Intent it = new Intent(this, DrawLayoutStatusActivity.class);
        startActivity(it);
    }
}
