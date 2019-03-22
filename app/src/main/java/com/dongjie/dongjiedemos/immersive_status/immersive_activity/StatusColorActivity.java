package com.dongjie.dongjiedemos.immersive_status.immersive_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.jaeger.library.StatusBarUtil;

public class StatusColorActivity extends BaseActivity {

    SeekBar mSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_color);
        StatusBarUtil.setColor(this, Color.BLUE);
        mSeekBar = findViewById(R.id.seek_bar);
        mSeekBar.setMax(255);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    // 不设置颜色情况下设置透明度，0- 255， 使用它设置的颜色会失效
//                    StatusBarUtil.setTranslucent( StatusColorActivity.this , progress) ;

                    // 给状态栏设置颜色，并且可以设置透明度， 透明度值：0-255
                    StatusBarUtil.setColor(StatusColorActivity.this, Color.BLUE, progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
