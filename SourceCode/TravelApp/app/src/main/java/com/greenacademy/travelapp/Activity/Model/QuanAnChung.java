package com.greenacademy.travelapp.Activity.Model;

/**
 * Created by DAVIDSON on 2/10/2017.
 */

public class QuanAnChung {
    public int Id;
    public String TenLoaiQuanAn;
    public String LinkAnh;
    public String MoTa;
    public double DanhGia;
    public int SoLuotXem;
    public int YeuThich;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenLoaiQuanAn() {
        return TenLoaiQuanAn;
    }

    public void setTenLoaiQuanAn(String tenLoaiQuanAn) {
        TenLoaiQuanAn = tenLoaiQuanAn;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public double getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(double danhGia) {
        DanhGia = danhGia;
    }

    public int getSoLuotXem() {
        return SoLuotXem;
    }

    public void setSoLuotXem(int soLuotXem) {
        SoLuotXem = soLuotXem;
    }

    public int getYeuThich() {
        return YeuThich;
    }

    public void setYeuThich(int yeuThich) {
        YeuThich = yeuThich;
    }
}
