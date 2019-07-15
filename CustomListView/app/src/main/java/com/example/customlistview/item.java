package com.example.customlistview;

public class item {
    private int Anh;
    private String thongBao;
    private String gioDang;

    public item(int anh, String thongBao, String gioDang) {
        Anh = anh;
        this.thongBao = thongBao;
        this.gioDang = gioDang;
    }

    public item() { }

    public int getAnh() {
        return Anh;
    }

    public void setAnh(int anh) {
        Anh = anh;
    }

    public String getThongBao() {
        return thongBao;
    }

    public void setThongBao(String thongBao) {
        this.thongBao = thongBao;
    }

    public String getGioDang() {
        return gioDang;
    }

    public void setGioDang(String gioDang) {
        this.gioDang = gioDang;
    }
}
