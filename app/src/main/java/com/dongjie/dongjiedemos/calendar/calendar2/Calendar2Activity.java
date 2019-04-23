package com.dongjie.dongjiedemos.calendar.calendar2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;

/**
 * GitHub地址：https://github.com/AigeStudio/DatePicker
 * 引用方式：implementation 'cn.aigestudio.datepicker:DatePicker:2.2.0'
 */
public class Calendar2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);
    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, CalendarStyle1Activity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {
        Intent it = new Intent(this, CalendarStyle2Activity.class);
        startActivity(it);
    }

    public void onButtonClick3(View v) {
        Intent it = new Intent(this, CalendarStyle3Activity.class);
        startActivity(it);
    }
}
