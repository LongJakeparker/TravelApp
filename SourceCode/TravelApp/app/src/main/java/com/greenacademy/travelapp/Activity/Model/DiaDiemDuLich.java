package com.greenacademy.travelapp.Activity.Model;

/**
 * Created by PhamNhuVu on 2/11/2017.
 */

public class DiaDiemDuLich {

    private int intId;
    private String strTenKhuVuc;
    private String strLinkAnh;
    private String strMota;
    private float flDanhGia;
    private int intSoLuotXem;
    private int intYeuThich;

    public DiaDiemDuLich() {
    }

    public DiaDiemDuLich(int intId, String strTenKhuVuc, String strLinkAnh, String strMota, float flDanhGia, int intSoLuotXem, int intYeuThich) {
        this.intId = intId;
        this.strTenKhuVuc = strTenKhuVuc;
        this.strLinkAnh = strLinkAnh;
        this.strMota = strMota;
        this.flDanhGia = flDanhGia;
        this.intSoLuotXem = intSoLuotXem;
        this.intYeuThich = intYeuThich;
    }

    public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public String getStrTenKhuVuc() {
        return strTenKhuVuc;
    }

    public void setStrTenKhuVuc(String strTenKhuVuc) {
        this.strTenKhuVuc = strTenKhuVuc;
    }

    public String getStrLinkAnh() {
        return strLinkAnh;
    }

    public void setStrLinkAnh(String strLinkAnh) {
        this.strLinkAnh = strLinkAnh;
    }

    public String getStrMota() {
        return strMota;
    }

    public void setStrMota(String strMota) {
        this.strMota = strMota;
    }

    public float getFlDanhGia() {
        return flDanhGia;
    }

    public void setFlDanhGia(float flDanhGia) {
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
}
