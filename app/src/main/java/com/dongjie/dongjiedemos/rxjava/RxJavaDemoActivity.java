package com.dongjie.dongjiedemos.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.rxjava.rxbus.RxBusActivity;
import com.dongjie.dongjiedemos.rxjava.rxjava.RxJavaActivity;

public class RxJavaDemoActivity extends BaseActivity {
    /**
     * RxJava GitHub地址：https://github.com/JakeWharton/RxBinding
     * RxJava参考：https://www.jianshu.com/p/cd3557b1a474
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_demo);
    }

    public void rxJavaBtClick(View v) {
        Intent it = new Intent(this, RxJavaActivity.class);
        startActivity(it);
    }

    public void rxBusBtClick(View v) {
        Intent it = new Intent(this, RxBusActivity.class);
        startActivity(it);
    }
}
