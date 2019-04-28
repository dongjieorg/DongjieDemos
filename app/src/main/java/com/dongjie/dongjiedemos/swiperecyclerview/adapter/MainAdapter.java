package com.dongjie.dongjiedemos.swiperecyclerview.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dongjie.dongjiedemos.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<String> mDataList;
    Activity mContext;
    public MainAdapter(Activity context, List<String> list) {
        mContext = context;
        mDataList = list;
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
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_menu_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mDataList.get(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        public void setData(String title) {
            this.tvTitle.setText(title);
        }
    }

}