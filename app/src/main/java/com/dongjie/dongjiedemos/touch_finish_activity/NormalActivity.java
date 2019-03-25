package com.dongjie.dongjiedemos.touch_finish_activity;

import android.os.Bundle;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.touch_finish_activity.touch_finish_lib.SwipeBackActivity;

public class NormalActivity extends SwipeBackActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_normal);
	}
}
