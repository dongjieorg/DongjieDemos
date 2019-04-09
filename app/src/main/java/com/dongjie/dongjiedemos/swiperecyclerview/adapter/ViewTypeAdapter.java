package com.dongjie.dongjiedemos.swiperecyclerview.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dongjie.dongjiedemos.R;

import java.util.List;

public class ViewTypeAdapter extends RecyclerView.Adapter<ViewTypeAdapter.ViewHolder> {
    private static final int VIEWTYPE_TWO = 1;
    private static final int VIEWTYPE_THREE = 2;
    private static final int VIEWTYPE_OTHER = 3;
    private List<String> mDataList;
    Activity mContext;
    public ViewTypeAdapter(Activity context, List<String> list) {
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

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        public void setData(String title) {
            this.tvTitle.setText(title);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return VIEWTYPE_THREE;
        } else if (position % 2 == 0) {
            return VIEWTYPE_TWO;
        } else {
            return VIEWTYPE_OTHER;
        }
    }
}