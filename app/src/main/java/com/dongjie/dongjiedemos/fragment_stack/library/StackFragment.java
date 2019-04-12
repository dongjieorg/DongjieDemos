package com.dongjie.dongjiedemos.fragment_stack.library;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.dongjie.dongjiedemos.R;

/**
 * extends this Fragment to facilitate the management of multiple fragment instances
 * User: chengwangyong(cwy545177162@163.com)
 * Date: 2016-01-18
 * Time: 18:19
 */
public abstract class StackFragment extends Fragment {

    StackActivity activity;
    public boolean mHasEnterAnim = true, mHasExitAnim = true; // 是否有进入和退出动画
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (StackActivity) context;
    }

    // 跳转页面
    public void pushFragment(StackFragment fragment) {
        activity.pushFragment(fragment);
    }

    // 跳转页面并指定启动模式， 目前支持标准模式和单例模式
    public void pushFragment(StackFragment fragment, int laucherMode) {
        activity.pushFragment(fragment, laucherMode);
    }

    // fragment退栈
    public void popFragment() {
        activity.popFragment();
    }

    // pop count层
    public void popFragment(int count) {
        activity.popFragment(count);
    }

    // 直接pop到指定的fragment
    public void popFragmentTo(Fragment fragment) {
        activity.popFragmentTo(fragment);
    }

    // 直接pop到指定的fragment
    public void popFragmentTo(String fragmentName) {
        activity.popFragmentTo(fragmentName);
    }

    // pop的还剩多少层
    public void popFragmentLeft(int count) {
        activity.popFragmentLeft(count);
    }

    // 返回栈顶fragment
    public Fragment peekFragment() {
        return activity.peekFragment();
    }

    // 清栈
    public void popAllFragment() {
        activity.popAllFragment();
    }

    // 通过fragment名字结束指定的fragment
    public void finishFragment(String fragmentName) {
        activity.finishFragment(fragmentName);
    }

    // 结束指定fragment
    public void finishFragment(StackFragment fragment) {
        activity.finishFragment(fragment);
    }

    // 结束当前fragment，相当于popFragment()
    public void finishFragment() {
        activity.finishFragment();
    }

    // 当启动模式指定为SingleTask的时候，当用户再次new此fragment实例的时候会调用，可以在里面刷新数据
    public void onNewIntent() {

    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Animation animation = null;
        if (enter) {
            if (mHasEnterAnim) {
                // 进入动画
                animation = AnimationUtils.loadAnimation(activity, R.anim.comm_slide_in_from_right);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        httpRequestDatas();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        } else {
            if (mHasExitAnim) {
                // 退出动画
                if (FgStackCommon.getInstance().isHasExitAnim()) {
                    animation = AnimationUtils.loadAnimation(activity, R.anim.comm_slide_out_to_right);
                }
                else {
                    // 多层退出时候只有第一个有动画， 其他没有
                    animation = null;
                }
            }
        }

        return animation;
    }

    // 请求数据放到此方法中， 不然界面会卡顿
    protected abstract void httpRequestDatas();
}
