package com.dongjie.dongjiedemos.big_image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;

/**
 * 码云地址：https://gitee.com/dzibin/IntensifyImageView
 */
public class BigImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
    }

    public void onClick1(View v) {
        Intent it = new Intent(this, BigImgSinglePreviewActivity.class);
        startActivity(it);
    }

    public void onClick2(View v) {
        Intent it = new Intent(this, BigImgMutiplePreviewActivity.class);
        startActivity(it);
    }
}
