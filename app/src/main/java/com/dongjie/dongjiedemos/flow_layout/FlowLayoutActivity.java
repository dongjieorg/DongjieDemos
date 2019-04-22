package com.dongjie.dongjiedemos.flow_layout;

import android.os.Bundle;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.tools.ToastUtils;

import java.util.ArrayList;

public class FlowLayoutActivity extends BaseActivity {

    MultipleFlowLayout flowLayout1, flowLayout2, flowLayout3, flowLayout4, flowLayout5, flowLayout6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);

        flowLayout1 = findViewById(R.id.flow_layout1);
        flowLayout2 = findViewById(R.id.flow_layout2);
        flowLayout3 = findViewById(R.id.flow_layout3);
        flowLayout4 = findViewById(R.id.flow_layout4);
        flowLayout5 = findViewById(R.id.flow_layout5);
        flowLayout6 = findViewById(R.id.flow_layout6);
        // 第一个中样式添加数据
        ArrayList<MultipleDynamicStateLabelBean> beans1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // 第二个参数lableState是lable上文字的颜色
            MultipleDynamicStateLabelBean bean = new MultipleDynamicStateLabelBean("测试item" + i, 1, true);
            beans1.add(bean);
        }

        for (int i = 0; i < 5; i++) {
            MultipleDynamicStateLabelBean bean = new MultipleDynamicStateLabelBean("测试的长item" + i, 1, true);
            beans1.add(bean);
        }
        flowLayout1.setLableData(beans1);
        flowLayout1.setOnLabelItemClickLister(new MultipleFlowLayout.OnLabelItemClickLister() {
            @Override
            public void onLabelItemClick(MultipleDynamicStateLabelBean dynamicStateLabelBean, int position) {
                ToastUtils.showToast(FlowLayoutActivity.this, "onItemClick" + position);
            }
        });
        flowLayout1.setOnLabelItemDeleteClickListener(new MultipleFlowLayout.OnLableItemDeleteClickListener() {
            @Override
            public boolean onLabelItemDeleteClick(MultipleDynamicStateLabelBean dynamicStateLableBean, int position) {
                ToastUtils.showToast(FlowLayoutActivity.this, "onDelClick1");
                return false;
            }
        });

        // 第二个中样式添加数据
        ArrayList<MultipleDynamicStateLabelBean> beans2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MultipleDynamicStateLabelBean bean = new MultipleDynamicStateLabelBean("测试item" + i, 1, true);
            // 设置单选
            bean.setSingle(true);
            beans2.add(bean);
        }

        for (int i = 0; i < 5; i++) {
            MultipleDynamicStateLabelBean bean = new MultipleDynamicStateLabelBean("测试的长item" + i, 1, true);
            bean.setSingle(true);
            beans2.add(bean);
        }
        flowLayout2.setLableData(beans2);

        // 第三个中样式添加数据
        ArrayList<MultipleDynamicStateLabelBean> beans3 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MultipleDynamicStateLabelBean bean = new MultipleDynamicStateLabelBean("测试item" + i, 1, true);
            beans3.add(bean);
        }

        for (int i = 0; i < 5; i++) {
            MultipleDynamicStateLabelBean bean = new MultipleDynamicStateLabelBean("测试的长item" + i, 1, true);
            beans3.add(bean);
        }
        flowLayout3.setLableData(beans3);
        flowLayout3.setOnLabelItemClickLister(new MultipleFlowLayout.OnLabelItemClickLister() {
            @Override
            public void onLabelItemClick(MultipleDynamicStateLabelBean dynamicStateLabelBean, int position) {
                ToastUtils.showToast(FlowLayoutActivity.this, "onItemClick" + position);
            }
        });

        // 第四个中样式添加数据
        ArrayList<MultipleDynamicStateLabelBean> beans4 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MultipleDynamicStateLabelBean bean = new MultipleDynamicStateLabelBean("测试item" + i, 1, true);
            beans4.add(bean);
        }

        for (int i = 0; i < 5; i++) {
            MultipleDynamicStateLabelBean bean = new MultipleDynamicStateLabelBean("测试的长item" + i, 1, true);
            beans4.add(bean);
        }
        flowLayout4.setLableData(beans4);

        // 第五个样式添加数据
        ArrayList<MultipleDynamicStateLabelBean> beans5 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MultipleDynamicStateLabelBean bean = new MultipleDynamicStateLabelBean("测试item" + i, 1, true);
            beans5.add(bean);
        }
        // 最后一个添加了一个加号，同时设置加号的点击事件
        flowLayout5.setHasAddIcon(true);
        flowLayout5.setOnAddItemClickLister(new MultipleFlowLayout.OnAddItemClickLister() {
            @Override
            public void onAddItemClick() {
                ToastUtils.showToast(FlowLayoutActivity.this, "加号被点击了");
            }
        });
        flowLayout5.setLableData(beans5);


        // 第六个样式添加数据
        ArrayList<MultipleDynamicStateLabelBean> beans6 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            MultipleDynamicStateLabelBean bean = new MultipleDynamicStateLabelBean("测试item" + i, 1, true);
            beans6.add(bean);
        }
        flowLayout6.setShowLineNumber(3);
        flowLayout6.setLableData(beans5);
    }
}
