package com.dongjie.dongjiedemos.big_image;

import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

import java.io.IOException;

import me.kareluo.intensify.image.IntensifyImage;
import me.kareluo.intensify.image.IntensifyImageView;

public class BigImgSinglePreviewActivity extends BaseActivity {
    private IntensifyImageView mIntensifyImageView;

    private String[] mPictures;

    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_img_single_preview);

        mRootView = findViewById(R.id.ll_root);
        mIntensifyImageView = (IntensifyImageView) findViewById(R.id.intensify_image);
        assert mIntensifyImageView != null;
        mIntensifyImageView.setOnScaleChangeListener(new IntensifyImage.OnScaleChangeListener() {
            @Override
            public void onScaleChange(float scale) {
                // 两手指缩放的时候的回调
            }
        });

        // 设置图片缩放度
//        mIntensifyImageView.setScale();

        // 设置ScaleType
        mIntensifyImageView.setScaleType(IntensifyImage.ScaleType.FIT_AUTO);

        try {
            mPictures = getAssets().list("pictures"); // 获取assets目录下的所有图片
            mIntensifyImageView.setImage(getAssets().open("pictures/xingren.jpg"));
            mRootView.setBackgroundResource(android.R.color.black); // 设置背景颜色
        } catch (IOException e) {

        }
    }
}
