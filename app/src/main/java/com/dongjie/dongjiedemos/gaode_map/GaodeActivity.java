package com.dongjie.dongjiedemos.gaode_map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;

/**
 * 高德地图key：9ce91b15d2bcff0b7ca7e629abc53068
 * sdk下载路径：https://lbs.amap.com/api/android-sdk/download
 * 引用了以下库：包括地图sdk、定位sdk
    AMap2DMap_5.2.0_AMapSearch_6.5.0_AMapLocation_4.5.0_20190121.jar
 */
public class GaodeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaode);


    }

    // 去地图选择地址界面
    public void onButtonClick1(View v) {
        Intent it = new Intent(this, SelectLocationActivity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {

    }
}
