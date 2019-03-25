package com.dongjie.dongjiedemos.zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.tools.LogUtils;
import com.dongjie.dongjiedemos.tools.ToastUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;
import com.yzq.zxinglibrary.encode.CodeCreator;

/**
 * GitHub地址：https://github.com/yuzhiqiang1993/zxing
 * 集成就一句代码：implementation 'com.github.yuzhiqiang1993:zxing:2.2.6'
 */
public class ZXingActivity extends BaseActivity {
    EditText mEditText;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);

        mEditText = findViewById(R.id.edit_text);
        mImageView = findViewById(R.id.imageview);
    }

    public void onButtonClick1(View v) {
        Intent intent = new Intent(this, CaptureActivity.class);
        /*ZxingConfig是配置类
         *可以设置是否显示底部布局，闪光灯，相册，
         * 是否播放提示音  震动
         * 设置扫描框颜色等
         * 也可以不传这个参数
         * */
        ZxingConfig config = new ZxingConfig();
        config.setPlayBeep(true);//是否播放扫描声音 默认为true
        config.setShake(true);//是否震动  默认为true
        config.setDecodeBarCode(true);//是否扫描条形码 默认为true
        config.setReactColor(R.color.colorAccent);//设置扫描框四个角的颜色 默认为白色
        config.setFrameLineColor(R.color.colorAccent);//设置扫描框边框颜色 默认无色
        config.setScanLineColor(R.color.colorAccent);//设置扫描线的颜色 默认白色
        config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
        intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            // 扫描二维码/条码回传
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                ToastUtils.showToast(this, content);
                LogUtils.showLog("扫描结果：" + content);
            }
        }
    }

    public void onButtonClick2(View v) {
        // 不带logo的话最后一个参数传null
        Bitmap bitmap = CodeCreator.createQRCode(mEditText.getText().toString(), 400, 400, null);
        mImageView.setImageBitmap(bitmap);
    }

    public void onButtonClick3(View v) {
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Bitmap bitmap = CodeCreator.createQRCode(mEditText.getText().toString(), 400, 400, logo);
        mImageView.setImageBitmap(bitmap);
    }

    public void onButtonClick4(View v) {
        Bitmap bitmap = createOneDCode(mEditText.getText().toString());
        mImageView.setImageBitmap(bitmap);
    }

    public Bitmap createOneDCode(String content) {
        try {
            // 生成一维条码,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
            BitMatrix matrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.CODE_128, 700, 200);
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (matrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    }
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);
            // 通过像素数组生成bitmap,具体参考api
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        }
        catch (Exception e) {
            ToastUtils.showToast(this, "请输入正确的一维码数据");
        }

        return null;
    }
}
