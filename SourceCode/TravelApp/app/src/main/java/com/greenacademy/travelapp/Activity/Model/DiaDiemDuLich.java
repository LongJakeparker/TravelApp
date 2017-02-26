package com.greenacademy.travelapp.Activity.Model;

/**
 * Created by PhamNhuVu on 2/11/2017.
 */

public class DiaDiemDuLich {

    private int intId;
    private String strTenDiaDiem;
    private String strMoTa;
    private double flDanhGia;
    private int intSoLuotXem;
    private int intYeuThich;
    private int intCheckIn;
    private int intIdKhuVuc;
    private String strLinkAnh;
    private double flLat;
    private double flLng;
    private String strDiaChi;

    public DiaDiemDuLich(String strTenDiaDiem, String strMoTa, double flDanhGia, int intSoLuotXem, int intYeuThich) {
        this.strTenDiaDiem = strTenDiaDiem;
        this.strMoTa = strMoTa;
        this.flDanhGia = flDanhGia;
        this.intSoLuotXem = intSoLuotXem;
        this.intYeuThich = intYeuThich;
    }

    public DiaDiemDuLich(int intId, String strTenDiaDiem, String strMoTa, double flDanhGia, int intSoLuotXem, int intYeuThich,
                         int intCheckIn, int intIdKhuVuc, String strLinkAnh, double flLat, double flLng, String strDiaChi) {
        this.intId = intId;
        this.strTenDiaDiem = strTenDiaDiem;
        this.strMoTa = strMoTa;
        this.flDanhGia = flDanhGia;
        this.intSoLuotXem = intSoLuotXem;
        this.intYeuThich = intYeuThich;
        this.intCheckIn = intCheckIn;
        this.intIdKhuVuc = intIdKhuVuc;
        this.strLinkAnh = strLinkAnh;
        this.flLat = flLat;
        this.flLng = flLng;
        this.strDiaChi = strDiaChi;
    }

    public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public String getStrTenDiaDiem() {
        return strTenDiaDiem;
    }

    public void setStrTenDiaDiem(String strTenDiaDiem) {
        this.strTenDiaDiem = strTenDiaDiem;
    }

    public String getStrMoTa() {
        return strMoTa;
    }

    public void setStrMoTa(String strMoTa) {
        this.strMoTa = strMoTa;
    }

    public double getFlDanhGia() {
        return flDanhGia;
    }

    public void setFlDanhGia(double flDanhGia) {
        this.flDanhGia = flDanhGia;
    }

    public int getIntSoLuotXem() {
        return intSoLuotXem;
    }

    public void setIntSoLuotXem(int intSoLuotXem) {
        this.intSoLuotXem = intSoLuotXem;
    }

    public int getIntYeuThich() {
        return intYeuThich;
    }

    public void setIntYeuThich(int intYeuThich) {
        this.intYeuThich = intYeuThich;
    }

    public int getIntCheckIn() {
        return intCheckIn;
    }

    public void setIntCheckIn(int intCheckIn) {
        this.intCheckIn = intCheckIn;
    }

    public int getIntIdKhuVuc() {
        return intIdKhuVuc;
    }

    public void setIntIdKhuVuc(int intIdKhuVuc) {
        this.intIdKhuVuc = intIdKhuVuc;
    }

    public String getStrLinkAnh() {
        return strLinkAnh;
    }

    public void setStrLinkAnh(String strLinkAnh) {
        this.strLinkAnh = strLinkAnh;
    }

    public double getFlLat() {
        return flLat;
    }

    public void setFlLat(double flLat) {
        this.flLat = flLat;
    }

    public double getFlLng() {
        return flLng;
    }

    public void setFlLng(double flLng) {
        this.flLng = flLng;
    }

    public String getStrDiaChi() {
        return strDiaChi;
    }

    public void setStrDiaChi(String strDiaChi) {
        this.strDiaChi = strDiaChi;
    }
}
