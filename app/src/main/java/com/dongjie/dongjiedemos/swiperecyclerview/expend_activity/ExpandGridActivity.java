package com.dongjie.dongjiedemos.swiperecyclerview.expend_activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.adapter.ExpandedAdapter;
import com.dongjie.dongjiedemos.swiperecyclerview.bean.Group;
import com.dongjie.dongjiedemos.swiperecyclerview.bean.GroupMember;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import com.yanzhenjie.recyclerview.widget.BorderItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ExpandGridActivity extends BaseActivity {
    private SwipeRecyclerView mRecyclerView;
    private ExpandedAdapter mAdapter;
    private List<Group> mDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        mRecyclerView.addItemDecoration(new BorderItemDecoration(ContextCompat.getColor(this, R.color.divider_color)));
        mRecyclerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                // 根据原position判断该item是否是parent item
                if (mAdapter.isParentItem(position)) {
                    // 换取parent position
                    int parentPosition = mAdapter.parentItemPosition(position);

                    // 判断parent是否打开了二级菜单
                    if (mAdapter.isExpanded(parentPosition)) {
                        mDataList.get(parentPosition).setExpanded(false);
                        mAdapter.notifyParentChanged(parentPosition);

                        // 关闭该parent下的二级菜单
                        mAdapter.collapseParent(parentPosition);
                    } else {
                        mDataList.get(parentPosition).setExpanded(true);
                        mAdapter.notifyParentChanged(parentPosition);

                        // 打开该parent下的二级菜单
                        mAdapter.expandParent(parentPosition);
                    }
                } else {
                    // 换取parent position
                    int parentPosition = mAdapter.parentItemPosition(position);
                    // 换取child position
                    int childPosition = mAdapter.childItemPosition(position);
                    String message = String.format("我是%1$d爸爸的%2$d儿子", parentPosition, childPosition);
                    Toast.makeText(ExpandGridActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });

        mAdapter = new ExpandedAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        refresh();
    }

    /**
     * 刷新数据
     */
    private void refresh() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Group group = new Group();
            group.setName("我是爸爸，我的号是 " + i);
            group.setMemberList(new ArrayList<GroupMember>());
            for (int j = 0; j < 10; j++) {
                GroupMember member = new GroupMember();
                member.setName("我是儿子，我的号是 " + j);
                group.getMemberList().add(member);
            }
            mDataList.add(group);
        }

        mAdapter.setGroupList(mDataList);
        mAdapter.notifyDataSetChanged();
    }
}
