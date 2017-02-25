package com.greenacademy.travelapp.Activity.Item;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 19/01/2017.
 */

public class ListMapItem {
    private int Id;
    private int IdLoaiDiaDiem;
    private String classifyLocation;
    private String nameLocation;
    private String addressLocation;
    private int rateLocation;

    public  ListMapItem(){}

    public ListMapItem(String classifyLocation, String nameLocation, String addressLocation, int rateLocation) {
        this.classifyLocation = classifyLocation;
        this.rateLocation = rateLocation;
        this.nameLocation = nameLocation;
        this.addressLocation = addressLocation;
    }

    public String getClassifyLocation() {
        return classifyLocation;
    }

    public void setClassifyLocation(String classifyLocation) {
        this.classifyLocation = classifyLocation;
    }

    public int getRateLocation() {
        return rateLocation;
    }

    public void setRateLocation(int rateLocation) {
        this.rateLocation = rateLocation;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public String getAddressLocation() {
        return addressLocation;
    }

    public void setAddressLocation(String describeLocation) {
        this.addressLocation = describeLocation;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdLoaiDiaDiem() {
        return IdLoaiDiaDiem;
    }

    public void setIdLoaiDiaDiem(int idLoaiDiaDiem) {
        IdLoaiDiaDiem = idLoaiDiaDiem;
    }
}
