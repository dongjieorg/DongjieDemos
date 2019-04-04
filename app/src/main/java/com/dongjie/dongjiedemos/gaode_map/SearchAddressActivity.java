package com.dongjie.dongjiedemos.gaode_map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.custom_widget.ClearEditText;
import com.dongjie.dongjiedemos.gaode_map.adapter.SearchAddressListAdapter;
import com.dongjie.dongjiedemos.gaode_map.bean.NearAddressBean;
import com.dongjie.dongjiedemos.gaode_map.event.SearchResultEvent;
import com.dongjie.dongjiedemos.gaode_map.event.StringEvent;

import java.util.List;

import de.greenrobot.event.EventBus;

public class SearchAddressActivity extends BaseActivity {
    RecyclerView mRecyclerView;
    ClearEditText clearEditText;
    TextView mNoResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_address);
        EventBus.getDefault().register(this);
        mRecyclerView = findViewById(R.id.rv_search);
        clearEditText = findViewById(R.id.et_serach);
        mNoResult = findViewById(R.id.tv_no_result);
        clearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EventBus.getDefault().post(new StringEvent(s.toString().trim()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(Object event) {
        if (event instanceof SearchResultEvent) {
            List<NearAddressBean> address = ((SearchResultEvent) event).getAddress();
            if (address != null || address.size() > 0) {
                mNoResult.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(OrientationHelper. VERTICAL);
                mRecyclerView.setLayoutManager(layoutManager);
                SearchAddressListAdapter addressListAdapter = new SearchAddressListAdapter(this, address);
                mRecyclerView.setAdapter(addressListAdapter);
                addressListAdapter.setOnItemclickListener(new SearchAddressListAdapter.OnItemClickListener() {
                    @Override
                    public void itemClick(NearAddressBean nearAddressBean, int position) {
                        Intent it = new Intent();
                        it.putExtra("address", nearAddressBean);
                        setResult(20, it);
                        finish();
                    }
                });
            } else {
                mNoResult.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }
        }
    }

    public void onCancelButtonClick(View v) {
        finish();
    }
}
