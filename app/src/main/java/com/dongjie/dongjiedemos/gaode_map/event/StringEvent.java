package com.dongjie.dongjiedemos.gaode_map.event;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Author     : yfwang
 * Date       : 2017/9/23 12:34
 */
public class StringEvent {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StringEvent(String address){

        this.address = address;
    }
}
