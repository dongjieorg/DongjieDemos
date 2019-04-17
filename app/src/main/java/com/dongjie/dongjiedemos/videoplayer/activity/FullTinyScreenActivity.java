package com.dongjie.dongjiedemos.videoplayer.activity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class FullTinyScreenActivity extends BaseActivity {
    JCVideoPlayerStandard jcVideoPlayerStandard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_tiny_screen);
        jcVideoPlayerStandard = findViewById(R.id.jc_video);

        jcVideoPlayerStandard.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子不信");
        Glide.with(this).load("http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360").into(jcVideoPlayerStandard.thumbImageView);
    }

    public void openTinyVideo(View v) {
        jcVideoPlayerStandard.startWindowTiny();
    }

    public void openFullScreenVideo(View v) {
        jcVideoPlayerStandard.startWindowFullscreen();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
