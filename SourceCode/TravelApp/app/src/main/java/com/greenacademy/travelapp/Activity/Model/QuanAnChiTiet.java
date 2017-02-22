package com.greenacademy.travelapp.Activity.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by GIT on 2/9/2017.
 */
// model cho 3 cái còn lại
public class QuanAnChiTiet implements Serializable, Parcelable {
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

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double lng) {
        Lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(TenQuanAn);
        parcel.writeString(MoTa);
        parcel.writeInt(DanhGia);
        parcel.writeInt(SoLuotXem);
        parcel.writeInt(YeuThich);
        parcel.writeInt(CheckIn);
        parcel.writeInt(LoaiQuanId);
        parcel.writeString(LinkAnh);
        parcel.writeDouble(Lat);
        parcel.writeDouble(Lng);
    }
}
