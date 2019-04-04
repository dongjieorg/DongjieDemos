package com.dongjie.dongjiedemos.gaode_map;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.gaode_map.adapter.AddressListAdapter;
import com.dongjie.dongjiedemos.gaode_map.bean.NearAddressBean;
import com.dongjie.dongjiedemos.gaode_map.event.SearchResultEvent;
import com.dongjie.dongjiedemos.gaode_map.event.StringEvent;
import com.dongjie.dongjiedemos.tools.LogUtils;
import com.dongjie.dongjiedemos.tools.PermissionUtils;
import com.dongjie.dongjiedemos.tools.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class SelectLocationActivity extends BaseActivity implements PermissionUtils.PermissionCallbacks, LocationSource, AMapLocationListener {
    private AMap aMap;
    private MapView mapView;      // 地图View
    private LocationSource.OnLocationChangedListener mListener;  // 定位监听
    private AMapLocationClient mlocationClient;         // 定位的类
    private AMapLocationClientOption mLocationOption;
    LatLng target; // 地图中心点
    Marker screenMarker;
    RecyclerView mRecyclerView;
    ImageView mLocationIcon;
    boolean isSearch = false; // 判断是否是搜索界面要搜数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);
        EventBus.getDefault().register(this);
        mapView = findViewById(R.id.mapView);
        mRecyclerView = findViewById(R.id.rv_list);
        mLocationIcon = findViewById(R.id.iv_location);
        aMap = mapView.getMap();
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                target = cameraPosition.target;
                if (screenMarker != null) {
                    jumpPoint(screenMarker);
                }
                doSearchQuery("", false);
            }
        });
        PermissionUtils.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
    }

    // 定位按钮点击事件
    public void onLocationIconClick(View v) {
        startLocation();
    }

    // 搜索按钮点击事件
    public void onSearchClick(View v) {
        Intent it = new Intent(this, SearchAddressActivity.class);
        startActivityForResult(it, 20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && resultCode == 20) {
            // 搜索到地址后显示到地图上
            NearAddressBean bean = (NearAddressBean) data.getSerializableExtra("address");
            target = new LatLng(Double.parseDouble(bean.getyOffset()), Double.parseDouble(bean.getxOffset()));
            aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(target, 15));
//            aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(target, 18, 30, 0)));
        }
    }

    // 返回按钮点击事件
    public void onFinishClick(View v) {
        finish();
    }

    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;
    private List<PoiItem> poiItems;// poi数据
    // 刚进来定位把定位地点移到地图中心，然后直接去查定位点周边。拖动地图重新获取中心点也会重新调用去查周边
    private void doSearchQuery(String keyWord, final boolean isSearch) {
        this.isSearch = isSearch;
        currentPage = 0;
        query = new PoiSearch.Query(keyWord, "", "");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(30);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        if (target != null) {
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                @Override
                public void onPoiSearched(PoiResult result, int rcode) {
                    if (rcode == AMapException.CODE_AMAP_SUCCESS) {
                        if (result != null && result.getQuery() != null) {// 搜索poi的结果
                            if (result.getQuery().equals(query)) {// 是否是同一条
                                poiItems = result.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                                if (poiItems != null && poiItems.size() > 0) {

                                    ArrayList<NearAddressBean> list = new ArrayList<>();
                                    for (int i = 0; i < poiItems.size(); i++) {
                                        NearAddressBean nearAddressBean = new NearAddressBean();
                                        nearAddressBean.setCityCode(poiItems.get(i).getCityCode());
                                        nearAddressBean.setName(poiItems.get(i).getTitle());
                                        nearAddressBean.setAddress(poiItems.get(i).getSnippet());
                                        nearAddressBean.setCity(poiItems.get(i).getCityName());
                                        nearAddressBean.setProvince(poiItems.get(i).getProvinceName());
                                        nearAddressBean.setxOffset(String.valueOf(poiItems.get(i).getLatLonPoint().getLongitude()));
                                        nearAddressBean.setyOffset(String.valueOf(poiItems.get(i).getLatLonPoint().getLatitude()));
                                        list.add(nearAddressBean);
                                    }

                                    if (isSearch) {
                                        EventBus.getDefault().post(new SearchResultEvent(list));
                                    }
                                    else {
                                        AddressListAdapter addressListAdapter = new AddressListAdapter(SelectLocationActivity.this, list);
                                        // 设置列表数据
                                        LinearLayoutManager layoutManager = new LinearLayoutManager(SelectLocationActivity.this);
                                        mRecyclerView.setLayoutManager(layoutManager);
                                        layoutManager.setOrientation(OrientationHelper. VERTICAL);
                                        mRecyclerView.setAdapter(addressListAdapter);
                                    }
                                }
                                else {
                                    ToastUtils.showToast(SelectLocationActivity.this, "没有搜索到");
                                }
                            }
                        }
                    }
                }

                @Override
                public void onPoiItemSearched(PoiItem poiItem, int i) {

                }
            });
            // 设置搜索区域为以lp点为圆心，其周围3000米范围
            poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(target.latitude, target.longitude), Integer.MAX_VALUE, true));
            poiSearch.searchPOIAsyn();// 异步搜索
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == 0 && perms.size() == 2) {
            aMap.setLocationSource(this);// 设置定位监听
            aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
            aMap.getUiSettings().setScaleControlsEnabled(false);
            // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false，会触发activate
            aMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                // 定位成功
                LogUtils.showLog(aMapLocation.getAddress());
                LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                addMarkerAndMoveCameraToCurrent(latLng);
                // 停止定位
                mlocationClient.stopLocation();
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode()+ ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
            }
        }
    }

    // 添加marker,并且地图显示这个marker
    public void addMarkerAndMoveCameraToCurrent(LatLng latLng) {
        aMap.clear();
        screenMarker = aMap.addMarker(new MarkerOptions().position(latLng).draggable(false)
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_site_2)));
        //设置Marker在屏幕上,不跟随地图移动
        screenMarker.setPositionByPixels(mapView.getWidth() / 2, mapView.getHeight() / 2);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    // 发起定位
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            startLocation();
        }
    }

    // 发起定位
    private void startLocation() {
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
        else {
            mlocationClient.startLocation();
        }
    }


    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    /**
     * marker点击时跳动一下
     */
    public void jumpPoint(final Marker marker) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        EventBus.getDefault().unregister(this);
        deactivate();
    }

    public void onEventMainThread(Object event) {
        if (event instanceof StringEvent) {
            String address = ((StringEvent) event).getAddress();
            doSearchQuery(address, true);
        }
    }
}
