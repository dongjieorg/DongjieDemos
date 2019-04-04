package com.dongjie.dongjiedemos.andpermission;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

public class PermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    // 自己封装的PermissionUtils
    public void onButtonClick1(View v) {
        Intent it = new Intent(this, PermissionUtilsActivity.class);
        startActivity(it);
    }

    // AndPermission
    public void onButtonClick2(View v) {
        Intent it = new Intent(this, AndPermissionActivity.class);
        startActivity(it);
    }
}
