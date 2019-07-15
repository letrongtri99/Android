package com.example.mixonfragment;

public class User {
    private String tenNguoi;
    private String sDthoai;
    private int hinhAnh;

    public User(String tenNguoi, String sDthoai, int hinhAnh) {
        this.tenNguoi = tenNguoi;
        this.sDthoai = sDthoai;
        this.hinhAnh = hinhAnh;
    }

    public User(String tenNguoi, String sDthoai) {
        this.tenNguoi = tenNguoi;
        this.sDthoai = sDthoai;
    }

    public User() { }

    public String getTenNguoi() {
        return tenNguoi;
    }

    public void setTenNguoi(String tenNguoi) {
        this.tenNguoi = tenNguoi;
    }

    public String getsDthoai() {
        return sDthoai;
    }

    public void setsDthoai(String sDthoai) {
        this.sDthoai = sDthoai;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
