package com.dongjie.dongjiedemos.fragment_stack.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.fragment_stack.library.StackFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends StackFragment {
    public Fragment3() {
        // Required empty public constructor
    }

    TextView mTextView;
    Button mBt, mBt2;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment1, container, false);
        mTextView = v.findViewById(R.id.text);
        mBt = v.findViewById(R.id.bt);
        mBt2 = v.findViewById(R.id.bt2);
        mTextView.setText("这是第三个页面");
        mBt.setText("去第四个页面 Standard");
        mBt2.setText("finish Fragment2");
        mBt2.setVisibility(View.VISIBLE);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new Fragment4());
            }
        });

        mBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finishFragment("Fragment2");
                finishFragment(new Fragment2());
            }
        });
    }

    @Override
    protected void httpRequestDatas() {

    }
}
