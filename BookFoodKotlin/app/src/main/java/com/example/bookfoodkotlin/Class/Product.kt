package com.example.bookfoodkotlin.Class

public class Product {
    private lateinit var pId: String
    private lateinit var baseOfUnit: String
    private lateinit var desc: String
    private lateinit var image: String
    private lateinit var pName: String
    private lateinit var unitPrice: String

    constructor(pId: String, baseOfUnit: String, desc: String, image: String, pName: String, unitPrice: String) {
        this.pId = pId
        this.baseOfUnit = baseOfUnit
        this.desc = desc
        this.image = image
        this.pName = pName
        this.unitPrice = unitPrice
    }

    constructor(){}

    fun getpId(): String {
        return pId
    }

    fun setpId(pId: String) {
        this.pId = pId
    }

    fun getBaseOfUnit(): String {
        return baseOfUnit
    }

    fun setBaseOfUnit(baseOfUnit: String) {
        this.baseOfUnit = baseOfUnit
    }

    fun getDesc(): String {
        return desc
    }

    fun setDesc(desc: String) {
        this.desc = desc
    }

    fun getImage(): String {
        return image
    }

    fun setImage(image: String) {
        this.image = image
    }

    fun getpName(): String {
        return pName
    }

    fun setpName(pName: String) {
        this.pName = pName
    }

    fun getUnitPrice(): String {
        return unitPrice
    }

    fun setUnitPrice(unitPrice: String) {
        this.unitPrice = unitPrice
    }
}