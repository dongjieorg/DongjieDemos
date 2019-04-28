package com.dongjie.dongjiedemos.share_element;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;

public class ShareElement2Activity extends BaseActivity {

    ImageView imgV = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element2);
        imgV = findViewById(R.id.image);
        Intent it = getIntent();
        if (it != null) {
            int res = it.getIntExtra("img_res", -1);
            if (res != -1) {
                imgV.setImageResource(res);
            }
        }
    }
}
