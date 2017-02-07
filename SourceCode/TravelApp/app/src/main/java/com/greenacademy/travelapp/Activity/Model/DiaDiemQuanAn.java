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
    private String tenDiaDiem;
    private String moTa;
    private float danhGia;
    private int soLuotXem;
    private int yeuThich;
    private int checkIn;
    private int idKhuVuc;
    private String linkAnh;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DiaDiemQuanAn() {
    }

    public DiaDiemQuanAn(int bitMapId, float lat, float lng, String id, String title, String tenDiaDiem, String moTa, float danhGia, int soLuotXem, int yeuThich, int checkIn, int idKhuVuc, String linkAnh) {
        this.bitMapId = bitMapId;
        this.lat = lat;
        this.lng = lng;
        this.id = id;
        this.title = title;
        this.tenDiaDiem = tenDiaDiem;
        this.moTa = moTa;
        this.danhGia = danhGia;
        this.soLuotXem = soLuotXem;
        this.yeuThich = yeuThich;
        this.checkIn = checkIn;
        this.idKhuVuc = idKhuVuc;
        this.linkAnh = linkAnh;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public float getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(float danhGia) {
        this.danhGia = danhGia;
    }

    public int getSoLuotXem() {
        return soLuotXem;
    }

    public void setSoLuotXem(int soLuotXem) {
        this.soLuotXem = soLuotXem;
    }

    public int getYeuThich() {
        return yeuThich;
    }

    public void setYeuThich(int yeuThich) {
        this.yeuThich = yeuThich;
    }

    public int getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    public int getIdKhuVuc() {
        return idKhuVuc;
    }

    public void setIdKhuVuc(int idKhuVuc) {
        this.idKhuVuc = idKhuVuc;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
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
