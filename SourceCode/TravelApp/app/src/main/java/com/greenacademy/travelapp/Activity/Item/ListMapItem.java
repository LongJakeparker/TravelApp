package com.greenacademy.travelapp.Activity.Item;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 19/01/2017.
 */

public class ListMapItem {
    private int classifyLocation;
    private String nameLocation;
    private String describeLocation;
    private String distanceLocation;

    public  ListMapItem(){}

    public ListMapItem(int classifyLocation, String nameLocation, String describeLocation, String distanceLocation) {
        this.classifyLocation = classifyLocation;
        this.distanceLocation = distanceLocation;
        this.nameLocation = nameLocation;
        this.describeLocation = describeLocation;
    }

    public int getClassifyLocation() {
        return classifyLocation;
    }

    public void setClassifyLocation(int classifyLocation) {
        this.classifyLocation = classifyLocation;
    }

    public String getDistanceLocation() {
        return distanceLocation;
    }

    public void setDistanceLocation(String distanceLocation) {
        this.distanceLocation = distanceLocation;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public String getDescribeLocation() {
        return describeLocation;
    }

    public void setDescribeLocation(String describeLocation) {
        this.describeLocation = describeLocation;
    }
}
