package com.dongjie.dongjiedemos.album;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.tools.LogUtils;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;

/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment {

    View v;
    ImageView imageView;
    public CameraFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_camera, container, false);
        imageView = v.findViewById(R.id.imageview);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Album.camera(this) // Camera function.
                .image() // Take Picture.
                .onResult(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        Bitmap bitmap = BitmapFactory.decodeFile(result);
                        imageView.setImageBitmap(bitmap);
                        LogUtils.showLog("返回地址：" + result);
                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(@NonNull String result) {
                        LogUtils.showLog(result);
                    }
                })
                .start();
    }
}
