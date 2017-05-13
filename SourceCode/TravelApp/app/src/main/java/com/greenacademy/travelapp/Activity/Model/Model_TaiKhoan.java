package com.greenacademy.travelapp.Activity.Model;

/**
 * Created by BINH on 1/17/2017.
 */

public class Model_TaiKhoan {
    private String tenNguoiDung;
    private String email;
    private String pass;

    public Model_TaiKhoan(String tenNguoiDung, String email, String pass) {
        this.tenNguoiDung = tenNguoiDung;
        this.email = email;
        this.pass = pass;
    }
    public Model_TaiKhoan() {
        this.tenNguoiDung = "";
        this.email = "";
        this.pass = "";
    }
    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
