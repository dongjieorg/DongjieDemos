package com.dongjie.dongjiedemos.swiperecyclerview.menu_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onButtonClick1(View v) {
        Intent it = new Intent(this, ListMenuActivity.class);
        startActivity(it);
    }

    public void onButtonClick2(View v) {
        Intent it = new Intent(this, GridMenuActivity.class);
        startActivity(it);
    }

    public void onButtonClick3(View v) {
        Intent it = new Intent(this, ViewTypeMenuActivity.class);
        startActivity(it);
    }

    public void onButtonClick4(View v) {
        Intent it = new Intent(this, VerticalMenuActivity.class);
        startActivity(it);
    }

    public void onButtonClick5(View v) {
        Intent it = new Intent(this, DefineMenuActivity.class);
        startActivity(it);
    }
}
