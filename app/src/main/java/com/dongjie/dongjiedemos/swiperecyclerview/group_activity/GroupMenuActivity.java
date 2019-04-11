package com.dongjie.dongjiedemos.swiperecyclerview.group_activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GroupMenuActivity extends BaseActivity {
    private GroupAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_menu);

        SwipeRecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 添加侧滑菜单后列表卡如狗
//        recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);

        mAdapter = new GroupAdapter();
        recyclerView.setAdapter(mAdapter);

        mAdapter.setListItems(createDataList());
    }

    private static class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {

        static final int VIEW_TYPE_NON_STICKY = R.layout.item_menu_main;
        static final int VIEW_TYPE_STICKY = R.layout.item_menu_sticky;

        private List<ListItem> mListItems = new ArrayList<>();

        @NonNull
        @Override
        public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(viewType, parent, false);
            return new GroupViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
            holder.bind(mListItems.get(position));
        }

        @Override
        public int getItemViewType(int position) {
            if (mListItems.get(position) instanceof StickyListItem) {
                return VIEW_TYPE_STICKY;
            }
            return VIEW_TYPE_NON_STICKY;
        }

        @Override
        public int getItemCount() {
            return mListItems.size();
        }

        void setListItems(List<String> newItems) {
            mListItems.clear();
            for (String item : newItems) {
                mListItems.add(new ListItem(item));
            }

            Collections.sort(mListItems, new Comparator<ListItem>() {
                @Override
                public int compare(ListItem o1, ListItem o2) {
                    return o1.text.compareToIgnoreCase(o2.text);
                }
            });

            StickyListItem stickyListItem = null;
            for (int i = 0, size = mListItems.size(); i < size; i++) {
                ListItem listItem = mListItems.get(i);
                String firstLetter = String.valueOf(listItem.text.charAt(1));
                if (stickyListItem == null || !stickyListItem.text.equals(firstLetter)) {
                    stickyListItem = new StickyListItem(firstLetter);
                    mListItems.add(i, stickyListItem);
                    size += 1;
                }
            }
            notifyDataSetChanged();
        }
    }

    private static class GroupViewHolder extends RecyclerView.ViewHolder {

        private TextView text;

        GroupViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tv_title);
        }

        void bind(ListItem item) {
            text.setText(item.text);
        }
    }

    private static class ListItem {
        protected String text;
        ListItem(String text) {
            this.text = text;
        }
    }

    private static class StickyListItem extends ListItem {
        StickyListItem(String text) {
            super(text);
        }
    }

    protected List<String> createDataList() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strings.add("第" + i + "个Item");
        }
        return strings;
    }
}
