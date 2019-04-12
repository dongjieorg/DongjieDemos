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
import com.dongjie.dongjiedemos.fragment_stack.library.FgStackCommon;
import com.dongjie.dongjiedemos.fragment_stack.library.StackFragment;
import com.dongjie.dongjiedemos.tools.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends StackFragment {
    public Fragment1() {
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
        mTextView.setText("这是第一个页面");
        mBt.setText("去第二个页面Standard");
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new Fragment2());
            }
        });
    }

    @Override
    public void onNewIntent() {
        super.onNewIntent();
        Bundle b = FgStackCommon.getInstance().getExtras();
        String data = b.getString("data");
        ToastUtils.showToast(getActivity(), "fragment1,onNewIntent:" + data);
    }

    @Override
    protected void httpRequestDatas() {

    }
}
