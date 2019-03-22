package com.dongjie.dongjiedemos.notification;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.dongjie.dongjiedemos.R;

public class NotificationClickJumpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_notification_click_jump);
    }
}
