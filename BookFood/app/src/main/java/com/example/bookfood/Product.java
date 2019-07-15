package com.example.bookfood;

public class Product {
    private String pId;
    private String baseOfUnit;
    private String desc;
    private String image;
    private String pName;
    private String unitPrice;

    public Product(String pId, String baseOfUnit, String desc, String image, String pName, String unitPrice) {
        this.pId = pId;
        this.baseOfUnit = baseOfUnit;
        this.desc = desc;
        this.image = image;
        this.pName = pName;
        this.unitPrice = unitPrice;
    }

    public Product() { }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getBaseOfUnit() {
        return baseOfUnit;
    }

    public void setBaseOfUnit(String baseOfUnit) {
        this.baseOfUnit = baseOfUnit;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}
