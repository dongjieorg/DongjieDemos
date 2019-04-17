package com.dongjie.dongjiedemos.videoplayer.activity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerSimple;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class JieCaoApiActivity extends BaseActivity {
    JCVideoPlayerSimple mJcVideoPlayerSimple;
    JCVideoPlayerStandard mJcVideoPlayerStandard;
    JCVideoPlayer.JCAutoFullscreenListener mSensorEventListener;
    SensorManager mSensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_cao_api);

        mJcVideoPlayerSimple = (JCVideoPlayerSimple) findViewById(R.id.simple_demo);
        mJcVideoPlayerSimple.setUp("http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子在家吗");
        mJcVideoPlayerSimple.loop = true;
        mJcVideoPlayerSimple.startButton.performClick();  // 自动执行
//        mJcVideoPlayerSimple.hideControlBottomContainer();   // 隐藏下面控制栏
//        mJcVideoPlayerSimple.hideStartBt();   // 隐藏开始按钮


        mJcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.jc_video);
        mJcVideoPlayerStandard.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子不信");

        Glide.with(this)
                .load("http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360")
                .into(mJcVideoPlayerStandard.thumbImageView);
        mJcVideoPlayerStandard.loop = true;
//        mJcVideoPlayerStandard.startButton.performClick(); // 代码点击开始按钮

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
    }

    public void playLocalVideo(View v) {
        // 播放本地视频
//        mJcVideoPlayerStandard.setUp(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/local_video.mp4"
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子不信");

        // 播放assets中的视频
//        mJcVideoPlayerStandard.setUp("file:///android_asset/local_video.mp4"
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子不信");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
        mSensorManager.unregisterListener(mSensorEventListener);
        JCVideoPlayer.clearSavedProgress(this, null);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
