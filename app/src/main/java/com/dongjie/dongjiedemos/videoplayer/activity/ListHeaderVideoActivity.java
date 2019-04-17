package com.dongjie.dongjiedemos.videoplayer.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bumptech.glide.Glide;
import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class ListHeaderVideoActivity extends BaseActivity {
    ListView listView;
    LinearLayout headerLayout;
    JCVideoPlayerStandard jcVideoPlayerStandard;
    boolean userClose = false;
    MyBroadcastRecever myBroadcastRecever;
    IntentFilter mFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_header_video);

        myBroadcastRecever = new MyBroadcastRecever();
        mFilter = new IntentFilter("tiny_back_click");
        registerReceiver(myBroadcastRecever, mFilter);

        listView = findViewById(R.id.listview);


        headerLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.video_header, null);
        jcVideoPlayerStandard = headerLayout.findViewById(R.id.jc_video);
        listView.addHeaderView(headerLayout);

        jcVideoPlayerStandard.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子坐这");
        Glide.with(this)
                .load("http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg")
                .into(jcVideoPlayerStandard.thumbImageView);

        // 初始化列表数据
        Map<String, String> keyValuePair = new HashMap<>();
        keyValuePair.put("key", "list item");
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(keyValuePair);
        }

        ListAdapter adapter = new SimpleAdapter(this, list,
                android.R.layout.simple_list_item_1, new String[]{"key"}, new int[]{android.R.id.text1});

        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                try {
                    if (totalItemCount > 1 && firstVisibleItem >= 1) {
                        if (JCVideoPlayerManager.getSecondFloor() == null && !userClose) {
                            jcVideoPlayerStandard.startWindowTiny();
                        }
                    }
                    else {
                        jcVideoPlayerStandard.clearFloatScreen();
                    }
                }
                catch (Exception e) {

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    class MyBroadcastRecever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("tiny_back_click".equals(action)) {
                userClose = true;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastRecever);
    }
}
