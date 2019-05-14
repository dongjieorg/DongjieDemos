package com.dongjie.dongjiedemos.rxjava.rxbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.blankj.rxbus.RxBus;
import com.dongjie.dongjiedemos.R;

public class RxBusActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus2);
    }

    public void onClick1(View v) {
        RxBus.getDefault().post("这是发送的事件");
    }

    public void onClick2(View v) {
        RxBus.getDefault().post("这是发送的事件，tag为my_tag", "my_tag");
    }

    public void onClick3(View v) {
        RxBusEvent event = new RxBusEvent();
        event.setAge(10);
        event.setName("小明");
        RxBus.getDefault().post(event);
    }

    public void onClick4(View v) {
        RxBusEvent event = new RxBusEvent();
        event.setAge(20);
        event.setName("小红");
        RxBus.getDefault().post(event, "my_tag");
    }
}
