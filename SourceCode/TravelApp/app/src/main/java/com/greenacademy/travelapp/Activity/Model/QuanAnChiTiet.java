package com.greenacademy.travelapp.Activity.Model;

/**
 * Created by GIT on 2/9/2017.
 */
// model cho 3 cái còn lại
public class QuanAnChiTiet {
    public int Id;
    public String TenQuanAn;
    public String MoTa;
    public int DanhGia;
    public int SoLuotXem;
    public int YeuThich;
    public int CheckIn;
    public int LoaiQuanId;
    public String LinkAnh;
    public double Lat;
    public double Lng;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenQuanAn() {
        return TenQuanAn;
    }

    public void setTenQuanAn(String tenQuanAn) {
        TenQuanAn = tenQuanAn;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(int danhGia) {
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

    public int getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(int checkIn) {
        CheckIn = checkIn;
    }

    public int getLoaiQuanId() {
        return LoaiQuanId;
    }

    public void setLoaiQuanId(int loaiQuanId) {
        LoaiQuanId = loaiQuanId;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(int lat) {
        Lat = lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(int lng) {
        Lng = lng;
    }
}
