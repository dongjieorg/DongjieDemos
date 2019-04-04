package com.dongjie.dongjiedemos.andpermission;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dongjie.dongjiedemos.MainActivity;
import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.tools.ToastUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AndPermissionFragment extends Fragment {

    public AndPermissionFragment() {
        // Required empty public constructor
    }

    View v;
    Button bt = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_and_permission, container, false);
        bt = v.findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndPermission.with(AndPermissionFragment.this)
                        .runtime()
                        .permission(Permission.Group.CAMERA)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                if (data.size() == 1) {
                                    ToastUtils.showToast(getActivity(), "权限全部同意");
                                }
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                ToastUtils.showToast(getActivity(), "权限l被拒");
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
                                startActivity(intent);
                            }
                        })
                        .start();
            }
        });
        return v;
    }
}
