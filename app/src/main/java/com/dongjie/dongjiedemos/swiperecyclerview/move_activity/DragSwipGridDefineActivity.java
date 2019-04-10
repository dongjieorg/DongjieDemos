package com.dongjie.dongjiedemos.swiperecyclerview.move_activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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
import com.yanzhenjie.recyclerview.touch.OnItemMoveListener;
import com.yanzhenjie.recyclerview.touch.OnItemMovementListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 拖拽支持自定义
 */
public class DragSwipGridDefineActivity extends BaseActivity {
    SwipeRecyclerView mRecyclerView;
    MainAdapter mAdapter;
    List<String> mDataList;
    View mHeaderView;
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

        // 给RecyclerView添加头
        mHeaderView = getLayoutInflater().inflate(R.layout.layout_header_switch, mRecyclerView, false);
        mRecyclerView.addHeaderView(mHeaderView);

        SwitchCompat switchCompat = mHeaderView.findViewById(R.id.switch_compat);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 控制是否可以侧滑删除。
                mRecyclerView.setItemViewSwipeEnabled(isChecked);
            }
        });

        mRecyclerView.setLongPressDragEnabled(true); // 长按拖拽，默认关闭。
        mRecyclerView.setItemViewSwipeEnabled(true); // 滑动删除，默认关闭。

        mRecyclerView.setOnItemMoveListener(new OnItemMoveListener() {
            @Override
            public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
                // 想让不同的ViewType之间可以拖拽，就是去掉这个判断。
                if (srcHolder.getItemViewType() != targetHolder.getItemViewType()) return false;

                // 添加了HeadView时，通过ViewHolder拿到的position都需要减掉HeadView的数量。
                int fromPosition = srcHolder.getAdapterPosition() - mRecyclerView.getHeaderCount();
                int toPosition = targetHolder.getAdapterPosition() - mRecyclerView.getHeaderCount();

                if (toPosition == 0) {// 保证第一个不被挤走。
                    return false;
                }
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++)
                        Collections.swap(mDataList, i, i + 1);
                } else {
                    for (int i = fromPosition; i > toPosition; i--)
                        Collections.swap(mDataList, i, i - 1);
                }

                mAdapter.notifyItemMoved(fromPosition, toPosition);

                return true;// 返回true表示处理了并可以换位置，返回false表示你没有处理并不能换位置。
            }

            @Override
            public void onItemDismiss(RecyclerView.ViewHolder srcHolder) {
                int position = srcHolder.getAdapterPosition() - mRecyclerView.getHeaderCount();
                mDataList.remove(position);
                mAdapter.notifyItemRemoved(position);
                Toast.makeText(DragSwipGridDefineActivity.this, "现在的第" + position + "条被删除。", Toast.LENGTH_SHORT).show();
            }
        });

        // 自定义拖拽控制参数。
        mRecyclerView.setOnItemMovementListener(mItemMovementListener);
    }

    /**
     * Item移动参数回调监听，这里自定义Item怎样移动。
     */
    private OnItemMovementListener mItemMovementListener = new OnItemMovementListener() {
        @Override
        public int onDragFlags(RecyclerView recyclerView, RecyclerView.ViewHolder targetViewHolder) {
            int adapterPosition = targetViewHolder.getAdapterPosition();
            if (adapterPosition == 0) { // 这里让HeaderView不能拖拽。
                return OnItemMovementListener.INVALID;// 返回无效的方向。
            }

            // 真实的Position：通过ViewHolder拿到的position都需要减掉HeadView的数量。
            int position = adapterPosition - mRecyclerView.getHeaderCount();

            // 假如让普通Item的第一个不能拖拽。
            if (position == 0) {
                return OnItemMovementListener.INVALID;// 返回无效的方向。
            }

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                // Grid可以上下左右拖拽。
                return OnItemMovementListener.LEFT | OnItemMovementListener.UP | OnItemMovementListener.RIGHT |
                        OnItemMovementListener.DOWN;
            } else if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager)layoutManager;

                // 横向List只能左右拖拽。
                if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                    return (OnItemMovementListener.LEFT | OnItemMovementListener.RIGHT);
                }
                // 竖向List只能上下拖拽。
                else {
                    return OnItemMovementListener.UP | OnItemMovementListener.DOWN;
                }
            }
            return OnItemMovementListener.INVALID;// 返回无效的方向。
        }

        @Override
        public int onSwipeFlags(RecyclerView recyclerView, RecyclerView.ViewHolder targetViewHolder) {
            int adapterPosition = targetViewHolder.getAdapterPosition();
            if (adapterPosition == 0) { // 这里让HeaderView不能侧滑删除。
                return OnItemMovementListener.INVALID;// 返回无效的方向。
            }

            // 真实的Position：通过ViewHolder拿到的position都需要减掉HeadView的数量。
            int position = adapterPosition - mRecyclerView.getHeaderCount();

            // 假如让普通Item的第一个不能侧滑删除。
            if (position == 0) {
                return OnItemMovementListener.INVALID;// 返回无效的方向。
            }

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                LinearLayoutManager manager = (LinearLayoutManager)layoutManager;
                // 横向Grid上下侧滑。
                if (manager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                    return ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                }
                // 竖向Grid左右侧滑。
                else {
                    return ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                }
            } else if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager manager = (LinearLayoutManager)layoutManager;
                // 横向List上下侧滑。
                if (manager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                    return OnItemMovementListener.UP | OnItemMovementListener.DOWN;
                }
                // 竖向List左右侧滑。
                else {
                    return OnItemMovementListener.LEFT | OnItemMovementListener.RIGHT;
                }
            }
            return OnItemMovementListener.INVALID;// 其它均返回无效的方向。
        }
    };

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
                SwipeMenuItem addItem = new SwipeMenuItem(DragSwipGridDefineActivity.this).setBackground(R.drawable.selector_green)
                    .setImage(R.mipmap.ic_action_add)
                    .setWidth(width)
                    .setHeight(height);
                swipeLeftMenu.addMenuItem(addItem); // 添加菜单到左侧。

                SwipeMenuItem closeItem = new SwipeMenuItem(DragSwipGridDefineActivity.this).setBackground(R.drawable.selector_red)
                    .setImage(R.mipmap.ic_action_close)
                    .setWidth(width)
                    .setHeight(height);
                swipeLeftMenu.addMenuItem(closeItem); // 添加菜单到左侧。
            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(DragSwipGridDefineActivity.this).setBackground(R.drawable.selector_red)
                    .setImage(R.mipmap.ic_action_delete)
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。

                SwipeMenuItem addItem = new SwipeMenuItem(DragSwipGridDefineActivity.this).setBackground(R.drawable.selector_green)
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
                Toast.makeText(DragSwipGridDefineActivity.this, "list第" + position + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(DragSwipGridDefineActivity.this, "list第" + position + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
        }
    };
}