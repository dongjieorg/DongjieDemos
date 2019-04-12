package com.dongjie.dongjiedemos.fragment_stack.library;

import android.os.Bundle;

public class FgStackCommon {
    private static FgStackCommon instance;
    // 获取单例
    public static FgStackCommon getInstance() {
        synchronized (FgStackCommon.class) {
            if (instance == null) {
                instance = new FgStackCommon();
            }
        }

        return instance;
    }

    boolean hasExitAnim = true;
    public void setmHasExitAnim(boolean hasExitAnim) {
        this.hasExitAnim = hasExitAnim;
    }

    public boolean isHasExitAnim() {
        return hasExitAnim;
    }

    Bundle bundle;
    public void putExtras(Bundle b) {
        bundle = b;
    }

    public Bundle getExtras() {
        return bundle;
    }
}
