package com.dongjie.dongjiedemos.fragment_stack.library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dongjie.dongjiedemos.R;

import java.util.Stack;

public abstract class StackActivity extends AppCompatActivity {
    private Stack<StackFragment> mBackStack = null;       // Fragment栈
    private Stack<String> mBackStackName = null;   // 保存fragment名字的栈
    private Stack<Integer> mBackStackMode = null;   // 保存fragment的启动mode
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.setId(R.id.framLayoutId);
        setContentView(frameLayout);
        StackFragment fragment = getRootFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.framLayoutId, fragment).commit();
        mBackStack = new Stack<>();
        mBackStackName = new Stack<>();
        mBackStackMode = new Stack<>();
    }

    protected abstract StackFragment getRootFragment();

    // 对外提供的方法以及返回键的处理------------------------------------------------------------------------------------------
    @Override
    public final boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mBackStack != null && mBackStack.size() > 0) {
                popFragment();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void addFragment(StackFragment fragment, int launchMode) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
//        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//            trans.setCustomAnimations(R.anim.comm_slide_in_from_right, R.anim.comm_scale_out);
        trans.add(R.id.framLayoutId, fragment);
//        trans.replace(R.id.framLayoutId, fg);
        trans.addToBackStack("mybackstack");
        trans.commitAllowingStateLoss();
        mBackStack.push(fragment);
        mBackStackName.push(fragment.getClass().getSimpleName());
        mBackStackMode.push(launchMode);
    }

    public void finishFragment(String fragmentName) {
        // backStack为空的时候，不允许pop
        if (mBackStack.size() == 0) {
            return;
        }

        int index = -1;
        for (int i = 0; i < mBackStack.size(); i++) {
            if (fragmentName.equals(mBackStackName.get(i))) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction trans = fm.beginTransaction();
            mBackStack.get(index).mHasExitAnim = false;
            trans.remove(mBackStack.get(index));
            trans.commitAllowingStateLoss();
            mBackStack.remove(index);
            mBackStackName.remove(index);
            mBackStackMode.remove(index);
        }
    }

    // 结束指定fragment
    public void finishFragment(StackFragment fragment) {
        // backStack为空的时候，不允许pop
        if (mBackStack.size() == 0) {
            return;
        }

        int index = -1;
        for (int i = 0; i < mBackStack.size(); i++) {
            if (fragment.getClass().getSimpleName().equals(mBackStackName.get(i))) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction trans = fm.beginTransaction();
            mBackStack.get(index).mHasExitAnim = false;
            trans.remove(mBackStack.get(index));
            trans.commitAllowingStateLoss();
            mBackStack.remove(index);
            mBackStackName.remove(index);
            mBackStackMode.remove(index);
        }
    }

    // 结束当前fragment
    public void finishFragment() {
        popFragment();
    }

    // 跳转页面
    public void pushFragment(StackFragment fragment) {
        boolean isExist = false;
        int position = 0;
        for (int i = 0; i < mBackStack.size(); i++) {
            if (mBackStack.get(i).getClass().getSimpleName().equals(fragment.getClass().getSimpleName())) {
                isExist = true;
                position = i;
                break;
            }
        }

        // 先检查是否已经在栈内
        if (isExist) {
            if (mBackStackMode.get(position) == FgMode.SingleTask) {
                while (true) {
                    StackFragment fg = mBackStack.peek();
                    if (!fg.getClass().getSimpleName().equals(fragment.getClass().getSimpleName())) {
                        popFragment();
                        FgStackCommon.getInstance().setmHasExitAnim(false);
                    } else {
                        fg.onNewIntent();
                        break;
                    }
                    FgStackCommon.getInstance().setmHasExitAnim(true);
                }
            }
            else {
                addFragment(fragment, FgMode.Standard);
            }
        }
        else {
            addFragment(fragment, FgMode.Standard);
        }
    }

    // 跳转页面并指定启动模式， 目前支持标准模式和单例模式
    public void pushFragment(StackFragment fragment, int laucherMode) {
        addFragment(fragment, laucherMode);
    }

    // fragment退栈
    public void popFragment() {
        // backStack为空的时候，不允许pop
        if (mBackStack.size() == 0) {
            return;
        }

        FragmentManager fm = getSupportFragmentManager();
        try {
            fm.popBackStackImmediate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Fragment fg = mBackStack.pop();
        FragmentTransaction trans = fm.beginTransaction();
        trans.remove(fg);
        trans.commitAllowingStateLoss();
        mBackStackName.pop();
        mBackStackMode.pop();
    }

    // pop count层
    public void popFragment(int count) {
        for (int i = 0; i < count; i++) {
            popFragment();
            FgStackCommon.getInstance().setmHasExitAnim(false);
        }
        FgStackCommon.getInstance().setmHasExitAnim(true);
    }

    // 直接pop到指定的fragment
    public void popFragmentTo(Fragment fragment) {
        while (true) {
            Fragment fg = mBackStack.peek();
            if (!fg.getClass().getSimpleName().equals(fragment.getClass().getSimpleName())) {
                popFragment();
                FgStackCommon.getInstance().setmHasExitAnim(false);
            }
            else {
                break;
            }
        }
        FgStackCommon.getInstance().setmHasExitAnim(true);
    }

    // 直接pop到指定的fragment
    public void popFragmentTo(String fragmentName) {
        while (true) {
            Fragment fg = mBackStack.peek();
            if (!fg.getClass().getSimpleName().equals(fragmentName)) {
                popFragment();
                FgStackCommon.getInstance().setmHasExitAnim(false);
            }
            else {
                break;
            }
        }
        FgStackCommon.getInstance().setmHasExitAnim(true);
    }

    // pop的还剩多少层
    public void popFragmentLeft(int count) {
        if (mBackStack.size() <= count) {
            return;
        }

        while (mBackStack.size() != count) {
            popFragment();
            FgStackCommon.getInstance().setmHasExitAnim(false);
        }
        FgStackCommon.getInstance().setmHasExitAnim(true);
    }

    // 返回栈顶fragment
    public Fragment peekFragment() {
        if (mBackStack.size() > 0) {
            return mBackStack.peek();
        }
        return null;
    }

    // 清栈
    public void popAllFragment() {
        if (mBackStack == null || mBackStack.size() == 0) {
            return;
        }

        int size = mBackStack.size();

        if (size > 0) {
            for (int i = 0; i < size; i++) {
                popFragment();
                FgStackCommon.getInstance().setmHasExitAnim(false);
            }
            FgStackCommon.getInstance().setmHasExitAnim(true);
        }
    }
}
