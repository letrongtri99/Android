package com.example.firebasedemo;

public class SinhVien {
    private String ten;
    private int namsinh;
    private String diachi;

    public SinhVien() {
    }

    public SinhVien(String ten, int namsinh, String diachi) {
        this.ten = ten;
        this.namsinh = namsinh;
        this.diachi = diachi;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getTen() {
        return ten;
    }

    public int getNamsinh() {
        return namsinh;
    }
}
