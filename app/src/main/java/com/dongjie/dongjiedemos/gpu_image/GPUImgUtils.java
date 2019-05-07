package com.dongjie.dongjiedemos.gpu_image;

import android.content.Context;
import android.graphics.Bitmap;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSaturationFilter;

public class GPUImgUtils {
    GPUImage gpuImage;
    //把图片加载和图片滤镜处理放在同一个方法中完成，供外界进行调用, progress在0-1之间
    public Bitmap getSaturationFilter(Context context, Bitmap bitmap, float progress) {
        gpuImage = new GPUImage(context);
        gpuImage.setImage(bitmap);
        gpuImage.setFilter(new GPUImageSaturationFilter(progress));
        bitmap = gpuImage.getBitmapWithFilterApplied();
        return bitmap;
    }

    public Bitmap getGrayscaleFilter(Context context, Bitmap bitmap) {
        gpuImage = new GPUImage(context);
        gpuImage.setImage(bitmap);
        gpuImage.setFilter(new GPUImageGrayscaleFilter());
        bitmap = gpuImage.getBitmapWithFilterApplied();
        return bitmap;
    }
}
