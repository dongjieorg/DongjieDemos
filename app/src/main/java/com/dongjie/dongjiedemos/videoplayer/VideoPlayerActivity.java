package com.dongjie.dongjiedemos.videoplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.videoplayer.activity.FullTinyScreenActivity;
import com.dongjie.dongjiedemos.videoplayer.activity.JieCaoApiActivity;
import com.dongjie.dongjiedemos.videoplayer.activity.ListHeaderVideoActivity;
import com.dongjie.dongjiedemos.videoplayer.activity.ListVideoActivity;
import com.dongjie.dongjiedemos.videoplayer.activity.MutiHolderListVideoActivity;
import com.dongjie.dongjiedemos.videoplayer.activity.PagerListVideoActivity;
import com.dongjie.dongjiedemos.videoplayer.activity.RecyclerViewVideoActivity;
import com.dongjie.dongjiedemos.videoplayer.activity.WebViewVideoActivity;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * github地址：https://github.com/open-android/JieCaoVideoPlayer
 * 当前是源码依赖， 源码依赖可以在上面改很多东西，逻辑和UI等都可以按照自己的要求来改
 */

public class VideoPlayerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, FullTinyScreenActivity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {
        Intent it = new Intent(this, ListHeaderVideoActivity.class);
        startActivity(it);
    }

    public void onButtonClick3(View v) {
        Intent it = new Intent(this, ListVideoActivity.class);
        startActivity(it);
    }

    public void onButtonClick4(View v) {
        Intent it = new Intent(this, RecyclerViewVideoActivity.class);
        startActivity(it);
    }

    public void onButtonClick5(View v) {
        Intent it = new Intent(this, PagerListVideoActivity.class);
        startActivity(it);
    }

    public void onButtonClick6(View v) {
        JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class, "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", "嫂子辛苦了");
    }

    public void onButtonClick7(View v) {
        Intent it = new Intent(this, MutiHolderListVideoActivity.class);
        startActivity(it);
    }

    public void onButtonClick8(View v) {
        Intent it = new Intent(this, WebViewVideoActivity.class);
        startActivity(it);
    }

    public void onButtonClick9(View v) {
        Intent it = new Intent(this, JieCaoApiActivity.class);
        startActivity(it);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
