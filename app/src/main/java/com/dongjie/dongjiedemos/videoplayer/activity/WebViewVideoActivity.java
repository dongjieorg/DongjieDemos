package com.dongjie.dongjiedemos.videoplayer.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;

import com.bumptech.glide.Glide;
import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class WebViewVideoActivity extends BaseActivity {
    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_video);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JCCallBack(), "jcvd");
        mWebView.loadUrl("file:///android_asset/jcvd.html");
    }

    public class JCCallBack {
        @JavascriptInterface
        public void adViewJieCaoVideoPlayer(final int width, final int height, final int top, final int left, final int index) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (index == 0) {
                        JCVideoPlayerStandard webVieo = new JCVideoPlayerStandard(WebViewVideoActivity.this);
                        webVieo.setUp("http://video.jiecao.fm/11/16/c/68Tlrc9zNi3JomXpd-nUog__.mp4",
                                JCVideoPlayer.SCREEN_LAYOUT_LIST, "嫂子骑大马");
                        Glide.with(WebViewVideoActivity.this)
                                .load("http://img4.jiecaojingxuan.com/2016/11/16/1d935cc5-a1e7-4779-bdfa-20fd7a60724c.jpg@!640_360")
                                .into(webVieo.thumbImageView);
                        ViewGroup.LayoutParams ll = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(ll);
                        layoutParams.y = JCUtils.dip2px(WebViewVideoActivity.this, top);
                        layoutParams.x = JCUtils.dip2px(WebViewVideoActivity.this, left);
                        layoutParams.height = JCUtils.dip2px(WebViewVideoActivity.this, height);
                        layoutParams.width = JCUtils.dip2px(WebViewVideoActivity.this, width);
                        mWebView.addView(webVieo, layoutParams);
                    } else {
                        JCVideoPlayerStandard webVieo = new JCVideoPlayerStandard(WebViewVideoActivity.this);
                        webVieo.setUp("http://video.jiecao.fm/11/14/xin/%E5%90%B8%E6%AF%92.mp4",
                                JCVideoPlayer.SCREEN_LAYOUT_LIST, "嫂子失态了");
                        Glide.with(WebViewVideoActivity.this)
                                .load("http://img4.jiecaojingxuan.com/2016/11/14/a019ffc1-556c-4a85-b70c-b1b49811d577.jpg@!640_360")
                                .into(webVieo.thumbImageView);
                        ViewGroup.LayoutParams ll = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(ll);
                        layoutParams.y = JCUtils.dip2px(WebViewVideoActivity.this, top);
                        layoutParams.x = JCUtils.dip2px(WebViewVideoActivity.this, left);
                        layoutParams.height = JCUtils.dip2px(WebViewVideoActivity.this, height);
                        layoutParams.width = JCUtils.dip2px(WebViewVideoActivity.this, width);
                        mWebView.addView(webVieo, layoutParams);
                    }

                }
            });

        }
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
}
