package com.example.trichatapp;

public class NhanVien {
    private String SDT;
    private String Ten;

    public NhanVien(String SDT, String ten) {
        this.SDT = SDT;
        Ten = ten;
    }

    public NhanVien() {
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }
}
