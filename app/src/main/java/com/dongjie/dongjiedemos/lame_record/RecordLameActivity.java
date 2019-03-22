package com.dongjie.dongjiedemos.lame_record;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.czt.mp3recorder.util.MP3Recorder;
import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.mp3.Player;

import java.io.File;

/**
 * 安卓中不支持直接录音保存成mp3格式，默认都是amr格式，但是iOS那边不支持amr格式，因此我们要把amr格式直接转化成mp3格式
 * 常用的最稳定的第三方库lame完全可以满足要求
 * 因为要引用so库中的方法，因此LameUtil必须放到com.czt.mp3recorder.util下面，否则调不到so库里的方法
 * com.czt.mp3recorder.util包下面四个类都是lame的工具类，用法很简单，看下面例子
 * 官网lame下载地址：https://sourceforge.net/projects/lame/files/lame/
 */

public class RecordLameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_lame);
    }

    MP3Recorder mRecorder = null;
    File mFilePath;
    // 开始录音
    public void onButtonClick(View v) {
        if (mRecorder != null && mRecorder.isRecording()) {
            Toast.makeText(this, "录音中", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                return;
            }

            File path = new File("/mnt/sdcard/");
            mFilePath = File.createTempFile("dongjie", ".mp3", path);
            mRecorder = new MP3Recorder(mFilePath);
            mRecorder.start();
            Toast.makeText(this, "录音开始", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {

        }
    }

    // 停止录音
    public void onButtonClick2(View v) {
        if (mRecorder != null && mRecorder.isRecording()) {
            mRecorder.stop();
            mRecorder = null;
            Toast.makeText(this, "文件保存在了sdcard上", Toast.LENGTH_SHORT).show();
        }
    }

    // 播放
    public void onButtonClick3(View v) {
        Player player = new Player(this);
        player.playFile(mFilePath.getAbsolutePath());
    }
}
