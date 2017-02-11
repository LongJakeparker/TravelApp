package com.greenacademy.travelapp.Activity.Model;

/**
 * Created by GIT on 2/11/2017.
 */

public class KhachSanItem {
    private String tenKhachSan;
    private String diaChi;
    private int soDanhGia;
    private int danhGia;
    private String giaTien;

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
