package com.dongjie.dongjiedemos.album;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.tools.LogUtils;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;
import com.yanzhenjie.album.Filter;

import java.util.ArrayList;

/**
 * 第三方的相册，GitHub地址:https://github.com/yanzhenjie/Album
 */
public class AlbumActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        setTitle("相册Demo");

        // 初始化相册， 设置加载方式， 可以自己实现，用ImageLoader或者用Glide
        Album.initialize(AlbumConfig.newBuilder(this)
                .setAlbumLoader(new MediaLoader())
                .build());
    }

    // 选择图片和视频
    public void onButtonClick1(View v) {
        Album.album(this) // Image and video mix options.
                .multipleChoice() // 设置多选， 单选是: singleChoice().设置单选后selectCount调用会报错
                .columnCount(2) // 页面图片列表上显示几列
                .selectCount(9)  // 可选择图片的数量， 一般都是9
                .camera(true) // 列表上是否有相机拍照按钮
                .cameraVideoQuality(1) // Video quality, [0, 1].
                .cameraVideoLimitDuration(Long.MAX_VALUE) // The longest duration of the video is in milliseconds.
                .cameraVideoLimitBytes(Long.MAX_VALUE) // Maximum size of the video, in bytes.
//                .checkedList() // To reverse the list.
//                .filterSize() // Filter the file size.
//                .filterMimeType() // Filter file format.
//                .filterDuration() // Filter video duration.
                .afterFilterVisibility(true) // Show the filtered files, but they are not available.
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        // 用户选择完成之后图片都会回传到这里
                        LogUtils.showLog("用户选择了：");
                        for (int i = 0; i < result.size(); i++) {
                            LogUtils.showLog(result.get(i).getPath());
                        }
                    }
                })
                .onCancel(new Action<String>() {
                    // 用户在选择图片界面点击返回键取消选择
                    @Override
                    public void onAction(@NonNull String result) {
                        LogUtils.showLog(result);
                    }
                })
                .start();
    }

    // 选择图片和视频
    public void onButtonClick2(View v) {
        Album.album(this)
                .singleChoice() // 单选
                .columnCount(3) // 设置界面上显示多少列
                .camera(true) // 列表上是否有拍照按钮
                .cameraVideoQuality(1) // Video quality, [0, 1].
                .cameraVideoLimitDuration(Long.MAX_VALUE) // The longest duration of the video is in milliseconds.
                .cameraVideoLimitBytes(Long.MAX_VALUE) // Maximum size of the video, in bytes.
//                .filterSize() // 过滤文件大小，不设置就不限制大小
//                .filterMimeType() // 过滤文件格式，不设置就不限制格式，图片视频只要在相册里面都会显示
//                .filterDuration() // 过滤视频的长度， 限制多长的视频才能显示，不设置就不限制
                .afterFilterVisibility(true) // Show the filtered files, but they are not available.
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        // 用户选择完成之后图片都会回传到这里
                        LogUtils.showLog("用户选择了：");
                        for (int i = 0; i < result.size(); i++) {
                            LogUtils.showLog(result.get(i).getPath());
                        }
                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        LogUtils.showLog(result);
                    }
                })
                .start();
    }

    // 选择图片
    public void onButtonClick3(View v) {
        Album.image(this) // Image selection.
                .multipleChoice()
                .camera(false)
                .columnCount(4)
                .selectCount(9)
//                .filterSize() // 过滤图片大小，不设置就不限制大小
                .filterMimeType(new Filter<String>() { // 过滤图片格式，只显示jpg和png的图片
                    @Override
                    public boolean filter(String attributes) {
                        return attributes.contains(".jpg") || attributes.contains(".png");
                    }
                }) // 过滤文件格式
                .afterFilterVisibility(true) // Show the filtered files, but they are not available.
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {

                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {

                    }
                })
                .start();
    }

    // 只选择视频
    public void onButtonClick4(View v) {
        Album.image(this) // Image selection.
                .multipleChoice()
                .camera(false)
                .columnCount(2)
                .selectCount(9)
//                .filterSize() // 过滤图片大小，不设置就不限制大小
                .filterMimeType(new Filter<String>() { // 过滤文件格式，这里只过滤视频
                    @Override
                    public boolean filter(String attributes) {
                        return attributes.toLowerCase().contains(".avi")
                                || attributes.toLowerCase().contains(".mov")
                                || attributes.toLowerCase().contains(".rmvb")
                                || attributes.toLowerCase().contains(".rm")
                                || attributes.toLowerCase().contains(".flv")
                                || attributes.toLowerCase().contains(".mp4")
                                || attributes.toLowerCase().contains(".3gp");
                    }
                }) // 过滤文件格式
                .afterFilterVisibility(true) // Show the filtered files, but they are not available.
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {

                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {

                    }
                })
                .start();
    }

    // 直接调用手机相机拍照
    public void onButtonClick5(View v) {
        Album.camera(this) // Camera function.
                .image() // Take Picture.
//                .filePath() // File save path, not required.
                .onResult(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        LogUtils.showLog("返回地址：" + result);
                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        LogUtils.showLog(result);
                    }
                })
                .start();
    }

    // 直接调用手机相机录视频
    public void onButtonClick6(View v) {
        Album.camera(this) // Camera function.
                .video() // 录像.
//                .filePath() // File save path, not required.
                .onResult(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        LogUtils.showLog("返回地址：" + result);
                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        LogUtils.showLog(result);
                    }
                })
                .start();
    }

    // 加载图片的类， 里面可以自己配置用什么来加载
    public class MediaLoader implements AlbumLoader {
        @Override
        public void load(ImageView imageView, AlbumFile albumFile) {
            load(imageView, albumFile.getPath());
        }

        @Override
        public void load(ImageView imageView, String url) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .crossFade()
                    .into(imageView);
        }
    }
}
