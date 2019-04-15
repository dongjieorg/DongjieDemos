package com.dongjie.dongjiedemos.viewpager.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dongjie.dongjiedemos.viewpager.fragment.ItemFragment;

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ItemFragment itemFragment = new ItemFragment();
        Bundle b = new Bundle();
        b.putInt("position", position);
        itemFragment.setArguments(b);
        return itemFragment;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
}
