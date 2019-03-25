package com.dongjie.dongjiedemos.album;

import android.os.Bundle;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

public class CameraInFragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_in_fragment);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new CameraFragment()).commit();
    }
}
