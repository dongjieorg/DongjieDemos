package com.dongjie.dongjiedemos.fragment_stack;

import com.dongjie.dongjiedemos.fragment_stack.fragments.MainRootFragment;
import com.dongjie.dongjiedemos.fragment_stack.library.StackActivity;
import com.dongjie.dongjiedemos.fragment_stack.library.StackFragment;


public class FragmentStackManagerActivity extends StackActivity {
    @Override
    protected StackFragment getRootFragment() {
        return new MainRootFragment();
    }

}
