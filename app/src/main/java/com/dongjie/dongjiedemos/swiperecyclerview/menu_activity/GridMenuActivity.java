package com.dongjie.dongjiedemos.swiperecyclerview.menu_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.swiperecyclerview.adapter.MainAdapter;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Grid列表侧滑菜单，支持菜单横向和竖向的。
 */
public class GridMenuActivity extends BaseActivity {
    SwipeRecyclerView mRecyclerView;
    MainAdapter mAdapter;
    List<String> mDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mRecyclerView.setOnItemMenuClickListener(mMenuItemClickListener);

        mDataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mDataList.add("第" + i + "个Item");
        }

        mAdapter = new MainAdapter(this, mDataList);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            {
                SwipeMenuItem addItem = new SwipeMenuItem(GridMenuActivity.this).setBackground(R.drawable.selector_green)
                    .setImage(R.mipmap.ic_action_add)
                    .setWidth(width)
                    .setHeight(height);
                swipeLeftMenu.addMenuItem(addItem); // 添加菜单到左侧。

                SwipeMenuItem closeItem = new SwipeMenuItem(GridMenuActivity.this).setBackground(R.drawable.selector_red)
                    .setImage(R.mipmap.ic_action_close)
                    .setWidth(width)
                    .setHeight(height);
                swipeLeftMenu.addMenuItem(closeItem); // 添加菜单到左侧。
            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(GridMenuActivity.this).setBackground(R.drawable.selector_red)
                    .setImage(R.mipmap.ic_action_delete)
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。

                SwipeMenuItem addItem = new SwipeMenuItem(GridMenuActivity.this).setBackground(R.drawable.selector_green)
                    .setText("添加")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加菜单到右侧。
            }
        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private OnItemMenuClickListener mMenuItemClickListener = new OnItemMenuClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(GridMenuActivity.this, "list第" + position + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(GridMenuActivity.this, "list第" + position + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };
}