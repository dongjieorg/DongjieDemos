package com.dongjie.dongjiedemos.vlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.vlayout.adapter.FixLayoutAdapter;
import com.dongjie.dongjiedemos.vlayout.adapter.FloatLayoutAdapter;
import com.dongjie.dongjiedemos.vlayout.adapter.GridHelperAdapter;
import com.dongjie.dongjiedemos.vlayout.adapter.LinearAdapter;
import com.dongjie.dongjiedemos.vlayout.adapter.OneToNAdapter;
import com.dongjie.dongjiedemos.vlayout.adapter.SingleLayoutAdapter;
import com.dongjie.dongjiedemos.vlayout.adapter.StaggeredGridLayoutAdapter;
import com.dongjie.dongjiedemos.vlayout.adapter.StickyLayoutAdapter;

import java.util.ArrayList;

/**
 * 官网地址：https://github.com/alibaba/vlayout
 *
 * 默认通用布局实现，解耦所有的View和布局之间的关系: Linear, Grid, 吸顶, 浮动, 固定位置等。
 *     LinearLayoutHelper: 线性布局
 *     GridLayoutHelper: Grid布局， 支持横向的colspan
 *     FixLayoutHelper: 固定布局，始终在屏幕固定位置显示，会被悬停挡住
 *     ScrollFixLayoutHelper: 固定布局，但之后当页面滑动到该图片区域才显示, 可以用来做返回顶部或其他书签等
 *     FloatLayoutHelper: 浮动布局，可以固定显示在屏幕上，但用户可以拖拽其位置
 *     ColumnLayoutHelper: 栏格布局，都布局在一排，可以配置不同列之间的宽度比值
 *     SingleLayoutHelper: 通栏布局，只会显示一个组件View
 *     OnePlusNLayoutHelper: 一拖N布局，可以配置1-5个子元素
 *     StickyLayoutHelper: stikcy布局， 可以配置吸顶或者吸底
 *     StaggeredGridLayoutHelper: 瀑布流布局，可配置间隔高度/宽度
 *
 * 作者：不识水的鱼
 * 链接：https://www.jianshu.com/p/5fb06a52a12d
 * 来源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */
public class VLayoutActivity extends BaseActivity {
    RecyclerView rvList;

    private ArrayList<String> lists = new ArrayList<>();
    private ArrayList<Integer> imgSrc = new ArrayList<>();
    private ArrayList<Integer> goodSrc = new ArrayList<>();
    private ArrayList<Integer> stagSrc = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout);
        rvList = findViewById(R.id.rv_list);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rvList.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        rvList.setLayoutManager(layoutManager);
        // 第二个参数一定要是false，否则列表滑动会卡顿
        DelegateAdapter adapters = new DelegateAdapter(layoutManager, false);

        initLinearData();

//        FixLayoutHelper，  固定在最上面， 滑动的时候会被悬停的FloatLayoutHelper挡住
        FixLayoutHelper fixHelper=new FixLayoutHelper(0,0);
        adapters.addAdapter(new FixLayoutAdapter(this,fixHelper));
//
        //ColumnLayoutHelper，这种方式尽量少用，可以使用GridLayoutHelper，完全可以替代ColumnLayoutHelper，对产品扩展也有好处
//        ColumnLayoutHelper columnLayoutHelper=new ColumnLayoutHelper();
//        adapters.addAdapter(new FixLayoutAdapter(this,columnLayoutHelper));

        //floatLayoutHelper      右下角悬浮着的那个设置按钮
        FloatLayoutHelper layoutHelper = new FloatLayoutHelper();
        layoutHelper.setAlignType(FixLayoutHelper.BOTTOM_RIGHT);
        layoutHelper.setDefaultLocation(100, 400);
        adapters.addAdapter(new FloatLayoutAdapter(this, layoutHelper));

        //singleHelper     最上面的ViewPager， 是一个banner第三方组件
        SingleLayoutHelper singHelper=new SingleLayoutHelper();
        singHelper.setBgColor(R.color.colorPrimary);
        singHelper.setMargin(5,0,5,5);
        adapters.addAdapter(new SingleLayoutAdapter(this,singHelper));

        initGridData();
        // 进行Grid布局， 天猫、聚划算、天猫国际、外卖、天猫超市、充值中心、飞猪旅行、领金币、拍卖、分类
        GridLayoutHelper gridHelper = new GridLayoutHelper(5);
        gridHelper.setMarginTop(30);
//        gridHelper.setWeights(new float[]{20.0f,20.0f,20.0f,20.0f,20.0f});
        //设置垂直方向条目的间隔
        gridHelper.setVGap(5);
        //设置水平方向条目的间隔
        gridHelper.setHGap(5);
        gridHelper.setMarginLeft(30);
        gridHelper.setMarginBottom(30);
        //自动填充满布局
        gridHelper.setAutoExpand(true);
        adapters.addAdapter(new GridHelperAdapter(imgSrc, gridHelper));

        //吸顶的Helper     汉堡、ANDROID、IOS、梨、JAVAEE，推到最上面会吸附在顶部
        StickyLayoutHelper stickyHelper = new StickyLayoutHelper();
        adapters.addAdapter(new StickyLayoutAdapter(stickyHelper));

        initOnePlusData();
        //onePlusNHelper， 一拖n的布局是固定的，不用程序刻意去控制布局，只需要把数据设置进去就好了，最多5条数据
        OnePlusNLayoutHelper helper = new OnePlusNLayoutHelper();
        helper.setBgColor(R.color.colorPrimary);
        helper.setPadding(5, 5, 5, 5);
        // 一拖n有两列， 设置第一列和第二列各占50%， 第二列分为两行，此处没有更多数据了，所以没有分
        helper.setColWeights(new float[]{50f});
        helper.setMargin(10, 20, 10, 10);
        adapters.addAdapter(new OneToNAdapter(goodSrc.subList(0, 2), helper));

        OnePlusNLayoutHelper helper2 = new OnePlusNLayoutHelper();
        helper2.setBgColor(R.color.colorPrimary);
        helper2.setPadding(5, 5, 5, 5);
        // 一拖n有两列， 设置第一列占的65%，第二列占35%， 第二列分为两行，此处没有更多数据了，所以没有分
        helper2.setColWeights(new float[]{65f,35f});
        helper.setMargin(10, 20, 10, 10);
        adapters.addAdapter(new OneToNAdapter(goodSrc.subList(2, 4), helper2));

        OnePlusNLayoutHelper helper3 = new OnePlusNLayoutHelper();
        helper3.setBgColor(0xffef8ba3);
        helper3.setAspectRatio(2.0f);
        // 一拖n有两列， 设置第一列占的40%，第二列占60%
        helper3.setColWeights(new float[]{40f});
        // 第二列分为两行， 这里设置第一行的高度占百分之30， 下面是3列会自动平分
        helper3.setRowWeight(30f);
        helper3.setMargin(10, 20, 10, 20);
        helper3.setPadding(10, 10, 10, 10);
        adapters.addAdapter(new OneToNAdapter(goodSrc.subList(4,9), helper3));

        //Linear 布局
        LinearLayoutHelper linearHelper = new LinearLayoutHelper(10);
        adapters.addAdapter(new LinearAdapter(this,lists, linearHelper));

        //StaggerGridLayoutHelper
        initStagData();
        StaggeredGridLayoutHelper stagHelp=new StaggeredGridLayoutHelper(2);
        stagHelp.setHGap(5);
        stagHelp.setVGap(5);
        adapters.addAdapter(new StaggeredGridLayoutAdapter(this,stagSrc,stagHelp));

        rvList.setAdapter(adapters);
    }

    private void initStagData() {
        stagSrc.add(R.mipmap.g1);
        stagSrc.add(R.mipmap.zl);
        stagSrc.add(R.mipmap.ic);
        stagSrc.add(R.mipmap.g4);
        stagSrc.add(R.mipmap.g5);
    }

    private void initOnePlusData() {
        goodSrc.add(R.mipmap.img1);
        goodSrc.add(R.mipmap.img2);
        goodSrc.add(R.mipmap.tow1);
        goodSrc.add(R.mipmap.two2);
        goodSrc.add(R.mipmap.g1);
        goodSrc.add(R.mipmap.g2);
        goodSrc.add(R.mipmap.g3);
        goodSrc.add(R.mipmap.g4);
        goodSrc.add(R.mipmap.g5);
    }

    private void initGridData() {
        imgSrc.add(R.mipmap.i1);
        imgSrc.add(R.mipmap.i2);
        imgSrc.add(R.mipmap.i3);
        imgSrc.add(R.mipmap.i4);
        imgSrc.add(R.mipmap.i5);
        imgSrc.add(R.mipmap.i6);
        imgSrc.add(R.mipmap.i7);
        imgSrc.add(R.mipmap.i8);
        imgSrc.add(R.mipmap.i9);
        imgSrc.add(R.mipmap.i10);
    }

    private void initLinearData() {
        for (int i = 0; i < 18; i++) {
            lists.add(" LinearHelper :" + i);
        }
    }
}
