package com.dongjie.dongjiedemos.andpermission;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.tools.PermissionUtils;
import com.dongjie.dongjiedemos.tools.ToastUtils;

import java.security.Permission;
import java.util.List;

/**
 * 适用步骤： activity和fragment都适用
 * 1、实现PermissionUtils.PermissionCallbacks
 * 2、重写onRequestPermissionsResult， 在里面添加：PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
 * 3、PermissionUtils.requestPermissions()来申请权限
 * 4、在onPermissionsGranted里面监听同意的权限，在onPermissionsDenied里面监听被拒绝的权限
 */
public class PermissionUtilsActivity extends BaseActivity implements PermissionUtils.PermissionCallbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_utils);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == 0 && perms.size() == 2) {
            ToastUtils.showToast(this, "权限都有了");
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == 0 && perms.size() > 0) {
            ToastUtils.showToast(this, "您拒绝了");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onButtonClick(View v) {
        PermissionUtils.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
    }
}
