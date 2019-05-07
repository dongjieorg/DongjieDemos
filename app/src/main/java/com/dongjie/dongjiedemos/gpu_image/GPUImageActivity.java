package com.dongjie.dongjiedemos.gpu_image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

/**
 * github地址：https://github.com/cats-oss/android-gpuimage
 * 参考讲解：https://blog.csdn.net/it_zjyang/article/details/52268918
 *
 * 备注：目前只写了两个滤镜的demo，在jp.co.cyberagent.android.gpuimage.filter下有所有的滤镜
 */
public class GPUImageActivity extends BaseActivity {
    ImageView imageView1, imageView2;
    GPUImgUtils gpuImgUtils;
    SeekBar seekBar;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpuimage);
        imageView1 = findViewById(R.id.image_view);
        imageView2 = findViewById(R.id.image_view2);
        seekBar = findViewById(R.id.seek_bar);
        gpuImgUtils = new GPUImgUtils();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.header);
        bitmap = gpuImgUtils.getSaturationFilter(this, bitmap, 100);
        imageView1.setImageBitmap(bitmap);
        seekBar.setMax(10);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    imageView1.setImageBitmap(gpuImgUtils.getSaturationFilter(GPUImageActivity.this, bitmap, progress * 0.1f));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.header);
        bitmap = gpuImgUtils.getGrayscaleFilter(this, bitmap);
        imageView2.setImageBitmap(bitmap);
    }
}
