package com.dongjie.dongjiedemos.gaode_map;

import android.app.Activity;
import android.os.Bundle;

import com.dongjie.dongjiedemos.R;

/**
 * sdk下载路径：https://lbs.amap.com/api/android-sdk/download
 * 引用了以下库：包括地图sdk、导航SDK6.6.0、定位SDK4.5.0、猎鹰SDK1.0.0
 * AMap3DMap_6.7.0_AMapNavi_6.6.0_AMapSearch_6.5.0_AMapTrack_1.1.0_AMapLocation_4.5.0_20190227.jar
 * libAMapSDK_MAP_v6_7_0.so
 * libAMapSDK_NAVI_v6_6_0.so
 * libmp3decoder.so
 * librtbt828.so
 * libwtbt828.so
 */
public class GaodeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaode);



    }
}
