package com.dongjie.dongjiedemos.custom_widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

import com.dongjie.dongjiedemos.R;

public class ClearEditText extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

    public OnClearListener onClearListener;
    private Drawable mClearDrawable;
    private Drawable mStick;
    private boolean hasFoucs;
    private onMyFocusChangeListener mListener;
    private OnRegExpListener mOnRegExpListener;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setOnMyFocusChangeListener(onMyFocusChangeListener listener) {
        this.mListener = listener;
    }

    private void init() {
        setSingleLine(true);
        mClearDrawable = getCompoundDrawables()[2];//left, top, right, bottom, 第三个是drawableRight
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(
                    R.drawable.common_search_delete_selector);
        }
        mClearDrawable.setBounds(0, 0
                , mClearDrawable.getIntrinsicWidth()
                , mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = getCompoundDrawables()[2].getBounds();
                int height = rect.height();
                int distance = (getHeight() - height) / 2;
                //	判断按下的点是否按到了那个清空按钮上面
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());//x在图标的最左边和最右边之间
                boolean isInnerHeight = y > distance && y < (distance + height);
                if (isInnerWidth && isInnerHeight) {
                    this.setText("");
                    if (onClearListener != null) {
                        onClearListener.onClear();
                    }
                }
            }
        }

        if (onClearEditTextTouchListener != null) {
            onClearEditTextTouchListener.onTouch(event);
        }
        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，
     * 输入长度为零，隐藏删除图标，否则，显示删除图标
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            if (mListener != null) {
                mListener.onFocusChange(v, true);
            }
            setClearIconVisible(getText().length() > 0);
        } else {
            if (mListener != null) {
                mListener.onFocusChange(v, false);
            }
            setClearIconVisible(false);
        }
    }

    // 设置没有右边的叉叉按钮
    boolean mHideDeleteIcon = false;
    public void setHideDeleteIcon() {
        mHideDeleteIcon = true;
        setClearIconVisible(false);
    }

    //设置右边有叉叉按钮
    public void setShowDeleteIcon(){
        mHideDeleteIcon = false;
        setClearIconVisible(false);
    }


    private void setClearIconVisible(boolean visible) {
        Drawable clear = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], clear, getCompoundDrawables()[3]);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFoucs) {
            if (!mHideDeleteIcon && s.length() > 0) {
                setClearIconVisible(true);
            }
            else {
                setClearIconVisible(false);
            }
        }

        if (onTextChangeListener != null) {
            onTextChangeListener.onTextChange(s.toString());
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * Creator by zhenwang.xiang on 2017/8/16 15:54
     * Description: 新增Edittext限制回调接口，如有限制只需要回调setOnRegExpListener方法，
     * 设置真确的正则表达式
     */

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new MyInputConnecttion(super.onCreateInputConnection(outAttrs), false);
    }

    public void setOnRegExpListener(OnRegExpListener regexplistener) {
        mOnRegExpListener = regexplistener;
    }

    public void setOnClearListener(OnClearListener onClearListener) {
        this.onClearListener = onClearListener;
    }

    public interface OnTextChangeListener {
        void onTextChange(String s);
    }

    OnTextChangeListener onTextChangeListener;
    public void setOnTextChangeListener(OnTextChangeListener listener) {
        onTextChangeListener = listener;
    }

    public interface onMyFocusChangeListener {
        void onFocusChange(View view, boolean hasFocus);
    }

    public interface OnRegExpListener {
        boolean onRegExp(CharSequence text);
    }

    public interface OnClearEditTextTouchListener {
        boolean onTouch(MotionEvent event);
    }

    OnClearEditTextTouchListener onClearEditTextTouchListener;
    public void setOnClearEditTextTouchListener(OnClearEditTextTouchListener listener) {
        onClearEditTextTouchListener = listener;
    }

    /**
     * 新增清除按钮监听
     */
    public interface OnClearListener {
        void onClear();
    }

    class MyInputConnecttion extends InputConnectionWrapper implements InputConnection {

        public MyInputConnecttion(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            if (mOnRegExpListener != null) {
                if (!mOnRegExpListener.onRegExp(text)) {
                    return false;
                }
            }
            return super.commitText(text, newCursorPosition);
        }
    }
}
