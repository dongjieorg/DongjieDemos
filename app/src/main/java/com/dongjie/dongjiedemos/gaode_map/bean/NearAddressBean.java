package com.dongjie.dongjiedemos.gaode_map.bean;

import java.io.Serializable;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : dongjie
 * Date       : 2019/4/3 20:12
 */
public class NearAddressBean implements Serializable {
    private String name;
    private String address;
    private String xOffset;
    private String yOffset;
    private String province;
    private String county;
    private String countryId;
    private String city;
    private String neighborhood;
    private String building;
    private String formatAddress;
    private String cityCode;
    private boolean isSelect;

    public String getFormatAddress() {
        return formatAddress;
    }

    public void setFormatAddress(String formatAddress) {
        this.formatAddress = formatAddress;
    }


    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getxOffset() {
        return xOffset;
    }

    public void setxOffset(String xOffset) {
        this.xOffset = xOffset;
    }

    public String getyOffset() {
        return yOffset;
    }

    public void setyOffset(String yOffset) {
        this.yOffset = yOffset;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }



}
