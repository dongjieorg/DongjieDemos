package com.dongjie.dongjiedemos.touch_finish_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.touch_finish_activity.touch_finish_lib.SwipeBackActivity;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends SwipeBackActivity {
	private List<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abslistview);
		
		ListView mListView = (ListView) findViewById(R.id.listView);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ListViewActivity.this, android.R.layout.simple_list_item_1, list){

					@Override
					public int getCount() {
						return super.getCount();
					}

					@Override
					public View getView(int position, View convertView,
							ViewGroup parent) {
						
						System.out.println("getView");
						return super.getView(position, convertView, parent);
					}
			
		};
		mListView.setAdapter(adapter);
		
		for (int i = 0; i <= 30; i++) {
			list.add("测试数据" + i);
		}


		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				startActivity(new Intent(ListViewActivity.this, NormalActivity.class));
			}
		});
	}


}
