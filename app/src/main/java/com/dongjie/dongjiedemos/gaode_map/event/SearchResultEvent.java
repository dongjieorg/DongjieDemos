package com.dongjie.dongjiedemos.gaode_map.event;


import com.dongjie.dongjiedemos.gaode_map.bean.NearAddressBean;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Author     : yfwang
 * Date       : 2017/9/23 12:34
 */
public class SearchResultEvent {
    private List<NearAddressBean> address;

    public List<NearAddressBean> getAddress() {
        return address;
    }

    public void setAddress(List<NearAddressBean> address) {
        this.address = address;
    }

    public SearchResultEvent(List<NearAddressBean> address){
        this.address = address;
    }
}
