package com.dongjie.dongjiedemos.viewpager.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.tools.LogUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {
    public ItemFragment() {
    }

    TextView mTextView;
    Button mBt;
    View v;
    int mPosition;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment1, container, false);
        mTextView = v.findViewById(R.id.text);
        mBt = v.findViewById(R.id.bt);
        mBt.setVisibility(View.GONE);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle b = getArguments();
        if (b != null) {
            mPosition = b.getInt("position");
            mTextView.setText("这是第" + mPosition + "Fragment");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            LogUtils.showLog("第" + mPosition + "个页面显示");
            // 加载数据可以放到这里
        }
        else {
            LogUtils.showLog("第" + mPosition + "个页面隐藏");
        }
    }
}
