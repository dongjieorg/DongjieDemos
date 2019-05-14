package com.dongjie.dongjiedemos.rxjava.rxbus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.rxbus.RxBus;
import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

/**
 * GitHub: https://github.com/Blankj/RxBus
 */
public class RxBusActivity extends BaseActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);
        textView = findViewById(R.id.text);

        RxBus.getDefault().subscribe(this, new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                textView.setText((s));
                throw new NullPointerException("");
            }
        });

        RxBus.getDefault().subscribe(this, "my_tag", new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                textView.setText(s);
            }
        });

        RxBus.getDefault().subscribe(this, new RxBus.Callback<RxBusEvent>() {
            @Override
            public void onEvent(RxBusEvent o) {
                textView.setText(o.toString());
            }
        });

        RxBus.getDefault().subscribe(this, "my_tag", new RxBus.Callback<RxBusEvent>() {
            @Override
            public void onEvent(RxBusEvent o) {
                textView.setText(o.toString());
            }
        });
    }

    public void onClick1(View v) {
        Intent it = new Intent(this, RxBusActivity2.class);
        startActivity(it);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        RxBus.getDefault().unregister(this);
    }
}
