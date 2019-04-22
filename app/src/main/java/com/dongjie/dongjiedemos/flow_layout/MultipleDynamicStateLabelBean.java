package com.dongjie.dongjiedemos.flow_layout;

import java.io.Serializable;

/**
 * Changed by xu.wang on 2019/1/17.
 * Description:带删除按钮的动态标签的实体类
 */

public class MultipleDynamicStateLabelBean implements Serializable {
    private String lableName;
    //lableState状态分为1(正常显示蓝色字体)，2(黑色字体),3(透明度70%黑色字体)
    private int lableState;
    //lableIsClick状态 true可以点击，false不可点击
    private boolean lableIsClick;
    //isSingle是否是单选
    private boolean isSingle;
    //isSelect是否被选中
    private boolean isSelect;
    private boolean isShowDelete;
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    public int getLableState() {
        return lableState;
    }

    public void setLableState(int lableState) {
        this.lableState = lableState;
    }

    public boolean isLableIsClick() {
        return lableIsClick;
    }

    public void setLableIsClick(boolean lableIsClick) {
        this.lableIsClick = lableIsClick;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public boolean isShowDelete() {
        return isShowDelete;
    }

    public void setShowDelete(boolean showDelete) {
        isShowDelete = showDelete;
    }

    public MultipleDynamicStateLabelBean(String lableName, int lableState, boolean lableIsClick) {
        this.lableName = lableName;
        this.lableState = lableState;
        this.lableIsClick = lableIsClick;
    }

    public MultipleDynamicStateLabelBean(String lableName, int lableState, boolean lableIsClick, boolean isSingle) {
        this.lableName = lableName;
        this.lableState = lableState;
        this.lableIsClick = lableIsClick;
        this.isSingle = isSingle;
    }

    public MultipleDynamicStateLabelBean(String lableName, int lableState, boolean lableIsClick, boolean isSingle, boolean isSelect) {
        this.lableName = lableName;
        this.lableState = lableState;
        this.lableIsClick = lableIsClick;
        this.isSingle = isSingle;
        this.isSelect = isSelect;
    }

    public MultipleDynamicStateLabelBean(String lableName, int lableState, boolean lableIsClick, boolean isSingle, boolean isSelect, boolean isShowDelete) {
        this.lableName = lableName;
        this.lableState = lableState;
        this.lableIsClick = lableIsClick;
        this.isSingle = isSingle;
        this.isSelect = isSelect;
        this.isShowDelete = isShowDelete;
    }

    @Override
    public String toString() {
        return "DynamicStateLableBean{" +
                "lableName='" + lableName + '\'' +
                ", lableState=" + lableState +
                ", lableIsClick=" + lableIsClick +
                ", isSingle=" + isSingle +
                ", isSelect=" + isSelect +
                '}';
    }
}
