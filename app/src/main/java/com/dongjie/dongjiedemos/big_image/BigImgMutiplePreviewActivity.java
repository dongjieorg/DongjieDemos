package com.dongjie.dongjiedemos.big_image;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.dongjie.dongjiedemos.R;

import java.io.IOException;

import me.kareluo.intensify.image.IntensifyImage;
import me.kareluo.intensify.image.IntensifyImageView;

public class BigImgMutiplePreviewActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ImagePageAdapter mAdapter;
    private static final String PIC_DIR = "pictures";
    private String[] mPictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_img_mutiple_preview);

        try {
            mPictures = getAssets().list(PIC_DIR);
        } catch (IOException e) {
        }

        mViewPager = (ViewPager) findViewById(R.id.vp_pager);
        mAdapter = new ImagePageAdapter();
        mViewPager.setAdapter(mAdapter);
    }

    class ImagePageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPictures.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            IntensifyImageView imageView = new IntensifyImageView(container.getContext());
            imageView.setScaleType(IntensifyImage.ScaleType.FIT_AUTO);
            try {
                imageView.setImage(getAssets().open(PIC_DIR + "/" + mPictures[position]));
            } catch (IOException e) {
            }
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
