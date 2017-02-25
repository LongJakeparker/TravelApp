package com.greenacademy.travelapp.Activity.Model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by GIT on 2/11/2017.
 */

public class KhachSanItem {
    private String tenKhachSan;
    private String diaChi;
    private int soDanhGia;
    private int danhGia;
    private String giaTien;
    private int id;
    private String moTa;
    private int soLuotXem;
    private int checkIn;
    private String linkAnh;
    private LatLng latLng;
    private int khuVucId;

    public int getKhuVucId() {
        return khuVucId;
    }

    public void setKhuVucId(int khuVucId) {
        this.khuVucId = khuVucId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuotXem() {
        return soLuotXem;
    }

    public void setSoLuotXem(int soLuotXem) {
        this.soLuotXem = soLuotXem;
    }

    public int getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public KhachSanItem() {
    }

    public KhachSanItem(String tenKhachSan, String diaChi, int soDanhGia, int danhGia, String giaTien) {

        this.tenKhachSan = tenKhachSan;
        this.diaChi = diaChi;
        this.soDanhGia = soDanhGia;
        this.danhGia = danhGia;
        this.giaTien = giaTien;
    }

    public String getTenKhachSan() {
        return tenKhachSan;
    }

    public void setTenKhachSan(String tenKhachSan) {
        this.tenKhachSan = tenKhachSan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSoDanhGia() {
        return soDanhGia;
    }

    public void setSoDanhGia(int soDanhGia) {
        this.soDanhGia = soDanhGia;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }
}
