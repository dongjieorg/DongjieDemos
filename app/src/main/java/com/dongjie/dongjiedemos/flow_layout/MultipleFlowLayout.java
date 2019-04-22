package com.dongjie.dongjiedemos.flow_layout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.tools.ConvertUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 流式布局标签，带删除按钮
 * 可以直接在xml中使用app:(horizontal_space、vertical_space、mDynamicStyle)
 * mDynamicStyle分两种模式，传参分别为（1（默认）、2、3、4）1代表标签没有单选和多选的选项，2为单选(默认为多选，可以在数据里面给每一项设置单选，isSingle==false）,
 * 3 与 1 逻辑相同，只是标签背景不同
 * 4 同 2 ，颜色与背景不同
 */

@SuppressWarnings("ResourceType")
public class MultipleFlowLayout extends ViewGroup {

    /**
     * 存储行的集合，管理行
     */
    private List<Line> mLines = new ArrayList<>();

    /**
     * 水平和竖直的间距
     */
    private float vertical_space;
    private float horizontal_space;

    // 当前行的对象
    private Line mCurrentLine;

    // 行的最大宽度，除去边距的宽度
    private int mMaxWidth;
    //默认为不设置自动计算
    private int mLineNumber = 0;
    private Context mContext;
    private OnLabelItemClickLister mOnLabelItemClickLister;
    private OnLableItemDeleteClickListener mOnLableItemDeleteClickLister;
    private int mDynamicStyle;
    private ColorStateList mColorStateList;
    private float mWidth; //每个条目的宽度
    private boolean isShowDelete;

    public MultipleFlowLayout(Context context) {
        this(context, null);
    }

    public MultipleFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        // 获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        horizontal_space = array.getDimension(R.styleable.FlowLayout_width_space, 0);
        vertical_space = array.getDimension(R.styleable.FlowLayout_height_space, 0);
        mDynamicStyle = array.getInteger(R.styleable.FlowLayout_dynamicStyle, 1);
        mWidth = array.getDimension(R.styleable.FlowLayout_custom_width, 0);
        mColorStateList = array.getColorStateList(R.styleable.FlowLayout_FlowTextColor);
        isShowDelete = array.getBoolean(R.styleable.FlowLayout_isShowDelete, false);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 每次测量之前都先清空集合，不然会覆盖掉以前
        mLines.clear();
        mCurrentLine = null;

        // 获取总宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // 计算最大的宽度
        mMaxWidth = width - getPaddingLeft() - getPaddingRight();

        // ******************** 测量子view ********************
        // 遍历获取子View
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 测量子view
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            // 测量完需要将子view添加到管理行的集合中，将行添加到管理行的集合中
            if (mCurrentLine == null) {
                // 初次添加第一个子view的时候
                mCurrentLine = new Line(mMaxWidth, horizontal_space);

                // 添加子view
                mCurrentLine.addView(childView);
                // 添加行
                mLines.add(mCurrentLine);
            } else {
                // 行中有子view的时候，判断时候能添加
                if (mCurrentLine.canAddView(childView)) {
                    // 继续往该行里添加
                    mCurrentLine.addView(childView);
                } else {
                    //如果mLineNumber不为0的时候就设置为用户设置显示多少行，如果用户不设置就自己动态计算行
                    if (mLineNumber != 0) {
                        if (mLines.size() < mLineNumber) {
                            //  添加到下一行
                            mCurrentLine = new Line(mMaxWidth, horizontal_space);
                            mCurrentLine.addView(childView);
                            mLines.add(mCurrentLine);
                        }
                    } else {
                        //  添加到下一行
                        mCurrentLine = new Line(mMaxWidth, horizontal_space);
                        mCurrentLine.addView(childView);
                        mLines.add(mCurrentLine);
                    }
                }
            }
        }
        // ******************** 测量自己 *********************
        // 测量自己只需要计算高度，宽度肯定会被填充满的
        int totalheight = getPaddingTop() + getPaddingBottom();
        for (int i = 0; i < mLines.size(); i++) {
            // 所有行的高度
            totalheight += mLines.get(i).height;
        }
        // 所有竖直的间距
        if (mLines.size() > 0) {
            totalheight += (mLines.size() - 1) * vertical_space;
        }
        // 测量
        setMeasuredDimension(width, totalheight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 这里只负责高度的位置，具体的宽度和子view的位置让具体的行去管理
        l = getPaddingLeft();
        t = getPaddingTop();
        for (int i = 0; i < mLines.size(); i++) {
            // 获取行
            Line line = mLines.get(i);
            // 管理
            line.layout(t, l);

            // 更新高度
            t += line.height;
            if (i != mLines.size() - 1) {
                // 不是最后一条就添加间距
                t += vertical_space;
            }
        }
    }

    /**
     * 内部类，行管理器，管理每一行的子view
     */
    public class Line {
        // 定义一个行的集合来存放子View
        private List<View> views = new ArrayList<>();
        // 行的最大宽度
        private int maxWidth;
        // 行中已经使用的宽度
        private int usedWidth;
        // 行的高度
        private int height;
        // 子ivew之间的距离
        private float space;

        // 通过构造初始化最大宽度和边距
        public Line(int maxWidth, float horizontalSpace) {
            this.maxWidth = maxWidth;
            this.space = horizontalSpace;
        }

        /**
         * 往集合里添加子view
         */
        public void addView(View view) {
            int childWidth = view.getMeasuredWidth();
            int childHeight = view.getMeasuredHeight();

            // 更新行的使用宽度和高度
            if (views.size() == 0) {
                // 集合里没有子view的时候
                if (childWidth > maxWidth) {
                    usedWidth = maxWidth;
                    height = childHeight;
                } else {
                    usedWidth = childWidth;
                    height = childHeight;
                }
            } else {
                usedWidth += childWidth + space;
                height = childHeight > height ? childHeight : height;
            }

            // 添加子view到集合
            views.add(view);
        }


        /**
         * 判断当前的行是否能添加子view
         *
         * @return
         */
        public boolean canAddView(View view) {
            // 集合里没有数据可以添加
            if (views.size() == 0) {
                return true;
            }

            // 最后一个子view的宽度大于剩余宽度就不添加
            if (view.getMeasuredWidth() > (maxWidth - usedWidth - space)) {
                return false;
            }

            // 默认可以添加
            return true;
        }

        /**
         * 指定子View显示的位置
         *
         * @param t
         * @param l
         */
        public void layout(int t, int l) {
            // 循环指定子view的位置
            for (View view : views) {
                // 获取宽高
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();

                int top = t;
                int left = l;
                int right = measuredWidth + left;
                int bottom = measuredHeight + top;
                // 指定位置
                view.layout(left, top, right, bottom);

                // 更新数据
                l += measuredWidth + space;
            }
        }
    }

    /**
     * 可以动态设置显示几行
     *
     * @param number
     */
    public void setShowLineNumber(int number) {
        mLineNumber = number;
    }

    /**
     * 设置点击事件
     *
     * @param onLabelItemClickLister
     */
    public void setOnLabelItemClickLister(OnLabelItemClickLister onLabelItemClickLister) {
        mOnLabelItemClickLister = onLabelItemClickLister;
    }

    /**
     * 设置删除点击事件
     */
    public void setOnLabelItemDeleteClickListener(OnLableItemDeleteClickListener mOnLabelItemDeleteClickLister) {
        this.mOnLableItemDeleteClickLister = mOnLabelItemDeleteClickLister;
    }


    //设置lable的数据(默认样式)
    public void setLableData(ArrayList<MultipleDynamicStateLabelBean> mDatas) {
        setLableData(mDatas, 1);
    }

    boolean hasAddIcon;
    public void setHasAddIcon(boolean hasAddIcon) {
        this.hasAddIcon = hasAddIcon;
    }

    /**
     * 设置lable的数据(自己设置样式1（默认）、2、3,4),不同的样式文字大小宽高也不一样
     *
     * @param mDatas
     */
    public void setLableData(final ArrayList<MultipleDynamicStateLabelBean> mDatas, final int style) {
        removeAllViews();
        if (mDatas != null && mDatas.size() > 0 || hasAddIcon) {
            // 循环添加TextView到容器
            for (int i = 0; i < mDatas.size(); i++) {
                MultipleDynamicStateLabelBean multipleDynamicStateLabelBean = mDatas.get(i);
                final View view = View.inflate(mContext, R.layout.item_dynamicstate_mutiple_lable, null);
                LinearLayout mLlDynamicLayle = (LinearLayout) view.findViewById(R.id.ll_dynamics_lable);
                TextView mLableName = (TextView) view.findViewById(R.id.tv_lable_name);
                LayoutParams params = mLableName.getLayoutParams();

                //删除按钮
                ImageView deleteIv = (ImageView) view.findViewById(R.id.delete);
                //设置是否显示
                if (isShowDelete) {
                    deleteIv.setVisibility(VISIBLE);
                } else {
                    if (multipleDynamicStateLabelBean.isShowDelete()) {
                        deleteIv.setVisibility(VISIBLE);
                    } else {
                        deleteIv.setVisibility(GONE);
                    }
                }
                //点击事件
                final int finalI = i;
                deleteIv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MultipleDynamicStateLabelBean bean = mDatas.get(finalI);
                        boolean isRemovedLabel = false;
                        if (null != mOnLableItemDeleteClickLister) {
                            //先让自定义的点击事件调用
                            isRemovedLabel = mOnLableItemDeleteClickLister.onLabelItemDeleteClick(bean, finalI);
                        }

                        if (!isRemovedLabel) {
                            //如果点击事件返回false则自己处理删除的逻辑，返回true则不处理
                            mDatas.remove(bean);
                            setLableData(mDatas, style);
                        }
                    }
                });

                if (mWidth != 0) {
                    params.width = (int) mWidth;
                }
                if (style == 1) {
                    params.height = ConvertUtils.dip2px(mContext, 29);
                    mLableName.setLayoutParams(params);
                    mLableName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                    mLableName.setPadding(ConvertUtils.dip2px(mContext, 15), 0, ConvertUtils.dip2px(mContext, 15), 0);
                } else if (style == 2) {
                    params.height = ConvertUtils.dip2px(mContext, 23);
                    mLableName.setLayoutParams(params);
                    mLableName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
                    mLableName.setPadding(ConvertUtils.dip2px(mContext, 12), 0, ConvertUtils.dip2px(mContext, 12), 0);
                } else if (style == 3) {
                    params.height = ConvertUtils.dip2px(mContext, 20);
                    mLableName.setLayoutParams(params);
                    mLableName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
                    mLableName.setPadding(ConvertUtils.dip2px(mContext, 10), 0, ConvertUtils.dip2px(mContext, 10), 0);
                } else if (style == 4) {
                    params.height = ConvertUtils.dip2px(mContext, 30);
                    mLableName.setLayoutParams(params);
                    mLableName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                    mLableName.setPadding(ConvertUtils.dip2px(mContext, 16), 0, ConvertUtils.dip2px(mContext, 16), 0);
                } else if (style == 5) {
                    params.height = ConvertUtils.dip2px(mContext, 33);
                    mLableName.setLayoutParams(params);
                    mLableName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
                    mLableName.setPadding(ConvertUtils.dip2px(mContext, 16), 0, ConvertUtils.dip2px(mContext, 16), 0);
                }
                mLableName.setText(multipleDynamicStateLabelBean.getLableName());
                int state = multipleDynamicStateLabelBean.getLableState();
                if (state == 1) {
                    mLableName.setTextColor(Color.parseColor("#cc337cff"));
                } else if (state == 2) {
                    mLableName.setTextColor(Color.parseColor("2c2f32"));
                } else if (state == 3) {
                    mLableName.setTextColor(Color.parseColor("4d2c2f32"));
                }
                if (mDynamicStyle == 1) {
                    mLlDynamicLayle.setBackgroundResource(R.drawable.lable_selector);
                } else if (mDynamicStyle == 2) {
                    mLlDynamicLayle.setBackgroundResource(R.drawable.choice_lable_selector);
                    if (mColorStateList != null) {
                        mLableName.setTextColor(mColorStateList);
                    } else {
                        ColorStateList colorStateList = getResources().getColorStateList(R.drawable.lable_select_text_color);
                        mLableName.setTextColor(colorStateList);
                    }
                } else if (mDynamicStyle == 3) {
                    mLlDynamicLayle.setBackgroundResource(R.drawable.mutible_lable_shape);
                } else if (mDynamicStyle == 4) {
                    mLableName.setTextColor(R.drawable.lable_select_text_color1);
                    mLlDynamicLayle.setBackgroundResource(R.drawable.choice_lable_selector1);
                }
                if (multipleDynamicStateLabelBean.isSelect()) {
                    view.setSelected(true);
                } else {
                    view.setSelected(false);
                }
                if (multipleDynamicStateLabelBean.isLableIsClick()) {
                    if (mDynamicStyle == 1 || mDynamicStyle == 3) {
                        // 设置点击事件
                        final int position = i;
                        view.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mOnLabelItemClickLister != null) {
                                    mOnLabelItemClickLister.onLabelItemClick(mDatas.get(position), position);
                                }
                            }
                        });
                    } else if (mDynamicStyle == 2 || mDynamicStyle == 4) {
                        // 设置点击事件
                        final int position = i;
                        view.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mDatas.get(position).isSingle()) {
                                    for (int j = 0; j < mDatas.size(); j++) {
                                        mDatas.get(j).setSelect(false);
                                    }
                                    mDatas.get(position).setSelect(!mDatas.get(position).isSelect());
                                } else {
                                    mDatas.get(position).setSelect(!mDatas.get(position).isSelect());
                                }
                                if (mOnLabelItemClickLister != null) {
                                    mOnLabelItemClickLister.onLabelItemClick(mDatas.get(position), position);
                                }
                                setLableData(mDatas, style);
                            }
                        });
                    }

                }
                this.addView(view);
            }
            if (hasAddIcon) {
                View addView = View.inflate(mContext, R.layout.multiple_flow_layout_add_view, null);
                ImageView ivAdd = addView.findViewById(R.id.image_add);
                ivAdd.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onAddItemClickLister != null) {
                            onAddItemClickLister.onAddItemClick();
                        }
                    }
                });
                this.addView(addView);
            }
        }
    }

    //点击事件回调接口
    public interface OnLabelItemClickLister {
        void onLabelItemClick(MultipleDynamicStateLabelBean dynamicStateLabelBean, int position);
    }

    //删除按钮点击事件
    public interface OnLableItemDeleteClickListener {
        /**
         * @param dynamicStateLableBean
         * @param position
         * @return 是否由点击事件处理逻辑，如果返回true则不自动删除标签，交由点击事件完成
         */
        boolean onLabelItemDeleteClick(MultipleDynamicStateLabelBean dynamicStateLableBean, int position);
    }

    //点击事件回调接口
    public interface OnAddItemClickLister {
        void onAddItemClick();
    }

    private OnAddItemClickLister onAddItemClickLister;

    public OnAddItemClickLister getOnAddItemClickLister() {
        return onAddItemClickLister;
    }

    public void setOnAddItemClickLister(OnAddItemClickLister onAddItemClickLister) {
        this.onAddItemClickLister = onAddItemClickLister;
    }
}
