package com.greenacademy.travelapp.Activity.Model;

/**
 * Created by PhamNhuVu on 2/11/2017.
 */

public class DiaDiemDuLich {

    private int intId;
    private String strTenDiaDiem;
    private String strMoTa;
    private double dbDanhGia;
    private int intSoLuotXem;
    private int intYeuThich;
    private int intCheckIn;
    private int intIdKhuVuc;
    private String strLinkAnh;
    private double dbLat;
    private double dbLng;
    private String strDiaChi;

    public DiaDiemDuLich() {
    }

    public DiaDiemDuLich(String strTenDiaDiem, String strMoTa, double dbDanhGia, int intSoLuotXem, int intYeuThich) {
        this.strTenDiaDiem = strTenDiaDiem;
        this.strMoTa = strMoTa;
        this.dbDanhGia = dbDanhGia;
        this.intSoLuotXem = intSoLuotXem;
        this.intYeuThich = intYeuThich;
    }

    public DiaDiemDuLich(int intId, String strTenDiaDiem, String strMoTa, double dbDanhGia, int intSoLuotXem, int intYeuThich,
                         int intCheckIn, int intIdKhuVuc, String strLinkAnh, double dbLat, double dbLng, String strDiaChi) {
        this.intId = intId;
        this.strTenDiaDiem = strTenDiaDiem;
        this.strMoTa = strMoTa;
        this.dbDanhGia = dbDanhGia;
        this.intSoLuotXem = intSoLuotXem;
        this.intYeuThich = intYeuThich;
        this.intCheckIn = intCheckIn;
        this.intIdKhuVuc = intIdKhuVuc;
        this.strLinkAnh = strLinkAnh;
        this.dbLat = dbLat;
        this.dbLng = dbLng;
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

    public double getDbDanhGia() {
        return dbDanhGia;
    }

    public void setDbDanhGia(double dbDanhGia) {
        this.dbDanhGia = dbDanhGia;
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

    public double getDbLat() {
        return dbLat;
    }

    public void setDbLat(double dbLat) {
        this.dbLat = dbLat;
    }

    public double getDbLng() {
        return dbLng;
    }

    public void setDbLng(double dbLng) {
        this.dbLng = dbLng;
    }

    public String getStrDiaChi() {
        return strDiaChi;
    }

    public void setStrDiaChi(String strDiaChi) {
        this.strDiaChi = strDiaChi;
    }
}
