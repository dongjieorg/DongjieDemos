package com.dongjie.dongjiedemos.fragment_stack.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.fragment_stack.library.FgStackCommon;
import com.dongjie.dongjiedemos.fragment_stack.library.StackFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends StackFragment {
    public Fragment4() {
        // Required empty public constructor
    }

    TextView mTextView;
    Button mBt;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment1, container, false);
        mTextView = v.findViewById(R.id.text);
        mBt = v.findViewById(R.id.bt);
        mTextView.setText("这是第四个页面");
        mBt.setText("回第一个页面");

        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("data", "我是回传的数据");
                FgStackCommon.getInstance().putExtras(b);
                pushFragment(new Fragment1());
            }
        });
        return v;
    }

    @Override
    protected void httpRequestDatas() {

    }
}
