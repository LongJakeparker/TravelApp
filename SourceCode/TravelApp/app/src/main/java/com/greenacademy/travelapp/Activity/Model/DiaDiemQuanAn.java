package com.greenacademy.travelapp.Activity.Model;

/**
 * Created by nguyenhuuanh on 21/01/2017.
 */

public class DiaDiemQuanAn {
    private int bitMapId;
    private float lat;
    private float lng;
    private String id;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DiaDiemQuanAn() {
    }

    public DiaDiemQuanAn(int bitMapId, String id, float lat, float lng) {
        this.bitMapId = bitMapId;
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }

    public int getBitMapId() {
        return bitMapId;
    }

    public void setBitMapId(int bitMapId) {
        this.bitMapId = bitMapId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
