package com.dongjie.dongjiedemos.fragment_stack.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.fragment_stack.library.FgMode;
import com.dongjie.dongjiedemos.fragment_stack.library.StackFragment;
import com.dongjie.dongjiedemos.tools.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainRootFragment extends StackFragment {


    public MainRootFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_root, container, false);
        Button bt = v.findViewById(R.id.bt);
        Button bt2 = v.findViewById(R.id.bt2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new Fragment1(), FgMode.Standard);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new Fragment1(), FgMode.SingleTask);
            }
        });
        return v;
    }

    @Override
    public void onNewIntent() {
        super.onNewIntent();
        ToastUtils.showToast(getActivity(), "MainRoot onNewIntent");
    }

    @Override
    protected void httpRequestDatas() {

    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return null;
    }
}
