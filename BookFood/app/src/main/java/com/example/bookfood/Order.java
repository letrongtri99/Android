package com.example.bookfood;

public class Order {
    private String status;
    private String pName;
    private String timeorder;
    private String totalitem;
    private String totalprice;
    private String uId;
    private String uName;
    private String image;


    public Order(String status, String pName, String timeorder, String totalitem, String totalprice, String uId, String uName) {
        this.status = status;
        this.pName = pName;
        this.timeorder = timeorder;
        this.totalitem = totalitem;
        this.totalprice = totalprice;
        this.uId = uId;
        this.uName = uName;
    }

    public Order(String status, String pName, String timeorder, String totalitem, String totalprice, String uId, String uName, String image) {
        this.status = status;
        this.pName = pName;
        this.timeorder = timeorder;
        this.totalitem = totalitem;
        this.totalprice = totalprice;
        this.uId = uId;
        this.uName = uName;
        this.image = image;
    }

    public Order() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getTimeorder() {
        return timeorder;
    }

    public void setTimeorder(String timeorder) {
        this.timeorder = timeorder;
    }

    public String getTotalitem() {
        return totalitem;
    }

    public void setTotalitem(String totalitem) {
        this.totalitem = totalitem;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
