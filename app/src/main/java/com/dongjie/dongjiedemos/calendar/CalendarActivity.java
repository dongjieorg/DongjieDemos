package com.dongjie.dongjiedemos.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.calendar.calendar1.Calendar1Activity;

public class CalendarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, Calendar1Activity.class);
        startActivity(it);
    }
}
