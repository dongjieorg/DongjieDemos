package com.dongjie.dongjiedemos.andpermission;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.base.BaseActivity;
import com.dongjie.dongjiedemos.tools.ToastUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

/**
 * github地址：https://github.com/yanzhenjie/AndPermission
 * fragment和activity都适用
 */
public class AndPermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_and_permission);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AndPermissionFragment()).commit();
    }

    // 权限全在Permission类中， 还封装了group，比如存储权限有读写两个，直接用group申请就可以，不过判断的时候要判断此group里面权限的个数
    public void onButtonClick1(View v) {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (data.size() == 2) {
                            ToastUtils.showToast(AndPermissionActivity.this, "权限全部同意");
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        ToastUtils.showToast(AndPermissionActivity.this, "权限l被拒");
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.fromParts("package", getPackageName(), null));
                        startActivity(intent);
                    }
                })
                .start();
    }
}
