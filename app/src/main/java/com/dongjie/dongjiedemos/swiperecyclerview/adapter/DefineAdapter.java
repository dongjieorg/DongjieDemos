package com.dongjie.dongjiedemos.swiperecyclerview.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.tools.LogUtils;
import com.dongjie.dongjiedemos.tools.ToastUtils;
import com.yanzhenjie.recyclerview.SwipeMenuLayout;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DefineAdapter extends RecyclerView.Adapter<DefineAdapter.ViewHolder> {

    private List<String> mDataList;
    Activity mContext;
    ArrayList<SwipeMenuLayout> swipeMenuLayoutList;
    int mPosition;
    public DefineAdapter(Activity context, List<String> list) {
        mContext = context;
        mDataList = list;
        swipeMenuLayoutList = new ArrayList<>();
    }

    public void notifyDataSetChanged(List<String> dataList) {
        this.mDataList = dataList;
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_menu_define, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (!swipeMenuLayoutList.contains(holder.swipeMenuLayout)) {
            swipeMenuLayoutList.add(holder.swipeMenuLayout);
        }
        holder.textView.setText(mDataList.get(position));
        holder.swipeMenuLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.showLog("touch:" + position);
                for (int i = 0; i < swipeMenuLayoutList.size(); i++) {
                    if (swipeMenuLayoutList.get(i).isMenuOpen()) {
                        swipeMenuLayoutList.get(i).smoothCloseMenu();
                    }
                }
                return false;
            }
        });
        holder.leftBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(mContext, "你点击了自定义侧滑左侧第一个按钮");
                holder.swipeMenuLayout.smoothCloseMenu();
            }
        });

        holder.leftBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.swipeMenuLayout.smoothCloseMenu();
                ToastUtils.showToast(mContext, "你点击了自定义侧滑左侧第二个按钮");
            }
        });

        holder.rightBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.swipeMenuLayout.smoothCloseMenu();
                ToastUtils.showToast(mContext, "你点击了自定义侧滑右侧第一个按钮");
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        SwipeMenuLayout swipeMenuLayout;
        Button leftBt1, leftBt2, rightBt;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            swipeMenuLayout = itemView.findViewById(R.id.swipe_layout);
            leftBt1 = itemView.findViewById(R.id.left_view1);
            leftBt2 = itemView.findViewById(R.id.left_view2);
            rightBt = itemView.findViewById(R.id.right_view);
            textView = itemView.findViewById(R.id.text);
        }

    }


}