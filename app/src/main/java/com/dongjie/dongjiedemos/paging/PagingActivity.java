package com.dongjie.dongjiedemos.paging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.arch.paging.PositionalDataSource;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.tools.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 经测试，这个是以页为单位，第一次直接加载两页，滑动到第二页的第一条的时候会自动加载第三页，滑动到第三页的第一条的时候
 * 会自动加载第四页
 */
public class PagingActivity extends BaseActivity {
    MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);

        mAdapter = new MyAdapter();
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)                         //配置分页加载的数量
                .setEnablePlaceholders(false)     //配置是否启动PlaceHolders
                .setInitialLoadSizeHint(20)              //初始化加载的数量
                .build();
        /**
         * LiveData<PagedList<DataBean>> 用LivePagedListBuilder生成
         * LivePagedListBuilder 用 DataSource.Factory<Integer,DataBean> 和PagedList.Config.Builder 生成
         * DataSource.Factory<Integer,DataBean> 用 PositionalDataSource<DataBean>
         */
        LiveData<PagedList<DataBean>> liveData = new LivePagedListBuilder(new MyDataSourceFactory(), config)
//                .setBoundaryCallback(null)
//                .setFetchExecutor(null)
                .build();
        liveData.observe(this,new Observer<PagedList<DataBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<DataBean> dataBeans) {
                mAdapter.submitList(dataBeans);
            }
        });
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class DataBean {
        public int id;
        public String content;
    }

    private class MyDataSourceFactory extends DataSource.Factory<Integer,DataBean>{
        @Override
        public DataSource<Integer, DataBean> create() {
            return new MyDataSource();
        }
    }

    private class MyDataSource extends PositionalDataSource<DataBean> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams params,@NonNull LoadInitialCallback<DataBean> callback) {
            List<DataBean> list = loadData(0, 20);
            callback.onResult(list,0,20);
        }

        @Override
        public void loadRange(@NonNull LoadRangeParams params
                ,@NonNull LoadRangeCallback<DataBean> callback) {
            callback.onResult(loadData(params.startPosition, 20));
        }

    }

    /**
     * 假设这里需要做一些后台线程的数据加载任务。
     *
     * @param startPosition
     * @param count
     * @return
     */
    private List<DataBean> loadData(int startPosition, int count) {
        List<DataBean> list = new ArrayList();

        for (int i = 0; i < count; i++) {
            DataBean data = new DataBean();
            data.id = startPosition + i;
            data.content = "测试的内容=" + data.id;
            list.add(data);
        }
        LogUtils.showLog("loaddata");
        return list;
    }

    class MyAdapter extends PagedListAdapter<DataBean, MyViewHolder> {
        public MyAdapter() {
            super(mDiffCallback);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(android.R.layout.simple_list_item_2,null);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder,int position) {
            DataBean data = getItem(position);
            holder.text1.setText(String.valueOf(position));
            holder.text2.setText(String.valueOf(data.content));
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text1;
        public TextView text2;

        public MyViewHolder(View itemView) {
            super(itemView);

            text1 = itemView.findViewById(android.R.id.text1);
            text1.setTextColor(Color.RED);

            text2 = itemView.findViewById(android.R.id.text2);
            text2.setTextColor(Color.BLUE);
        }
    }

    private DiffUtil.ItemCallback<DataBean> mDiffCallback = new DiffUtil.ItemCallback<DataBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull DataBean oldItem, @NonNull DataBean newItem) {
            Log.d("DiffCallback","areItemsTheSame");
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DataBean oldItem,@NonNull DataBean newItem) {
            Log.d("DiffCallback","areContentsTheSame");
            return (oldItem == newItem);
        }
    };
}
