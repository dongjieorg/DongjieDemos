package com.dongjie.dongjiedemos.gaode_map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.gaode_map.constant.MapConstants;

public class GoNavActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_nav);
    }

    // 直接调起百度地图导航， 百度地图包名：
    public void onButtonClick1(View v) {
        goToBaiduMap(this, "西安", MapConstants.XIAN.latitude + "", MapConstants.XIAN.longitude + "");
    }

    // 直接调起高德地图导航，高德地图包名：com.autonavi.minimap
    public void onButtonClick2(View v) {
        goToGaodeMap(this, "西安", MapConstants.XIAN.latitude+"", MapConstants.XIAN.longitude+"");
    }

    // 直接调起腾讯地图导航
    public void onButtonClick3(View v) {
        goTencentMap(null, null, MapConstants.XIAN.latitude+","+MapConstants.XIAN.longitude, "西安", "DongjieDemos");
    }

    // 调起百度地图导航
    // 参数：要去的地方的名字、经纬度， 官网：http://lbsyun.baidu.com/index.php?title=uri/api/android
    public void goToBaiduMap(Context context, String dName, String latitude, String longitude) {
        String desName = null;
        if (dName == null) {
            desName = "终点";
        }
        else {
            desName = dName;
        }
        String uri = "baidumap://map/direction"
                + "?origin=我的位置"
                +"&destination=name:" + desName + "|latlng:"+latitude+","+longitude
                +"&coord_type=bd09ll"
                + "&mode=driving"
                + "&src=andr.companyName.appName";//src为统计来源必填，companyName、appName是公司名和应用名
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(uri));
        intent.addCategory("android.intent.category.DEFAULT");
        context.startActivity(intent);
    }

    // 官网：https://lbs.amap.com/api/uri-api/guide/travel/route
    public void goToGaodeMap(Context context, String dName, String latitude,String longitude) {
        String desName = null;
        if (dName == null) {
            desName = "终点";
        }
        else {
            desName = dName;
        }
        //默认驾车
        String uri = "amapuri://route/plan/"
                + "?dlat="+ latitude+"&dlon="+longitude
                + "&sname=我的位置"
                + "&dname=" + desName
                + "&dev=1"
                + "&t=0";
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(uri));
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setPackage("com.autonavi.minimap");
        context.startActivity(intent);
    }

    // 腾讯地图，参数参考：https://lbs.qq.com/uri_v1/guide-mobile-navAndRoute.html
    // 参数：起始点坐标，起始点名字，重点坐标，终点名字，自己app名字
    private void goTencentMap(String startPoint, String startAddressName, String endPoint, String endAddressName, String appName){
        if (startPoint != null && startAddressName != null) {
            Uri uri = Uri.parse("qqmap://map/routeplan?type=drive" + "&from=" + startAddressName + "&fromcoord=" + startPoint + "&to=" + endAddressName + "&tocoord=" + endPoint + "&referer=" + appName);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        else {
            Uri uri = Uri.parse("qqmap://map/routeplan?type=drive" + "&to=" + endAddressName + "&tocoord=" + endPoint + "&referer=" + appName);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }
}
