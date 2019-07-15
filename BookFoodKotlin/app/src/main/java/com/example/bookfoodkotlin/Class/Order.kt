package com.example.bookfoodkotlin.Class

class Order {
    private lateinit var status: String
    private lateinit var pName: String
    private lateinit var timeorder: String
    private lateinit var totalitem: String
    private lateinit var totalprice: String
    private lateinit var uId: String
    private lateinit var uName: String
    private lateinit var image: String


    constructor(
        status: String,
        pName: String,
        timeorder: String,
        totalitem: String,
        totalprice: String,
        uId: String,
        uName: String
    ) {
        this.status = status
        this.pName = pName
        this.timeorder = timeorder
        this.totalitem = totalitem
        this.totalprice = totalprice
        this.uId = uId
        this.uName = uName
    }

    constructor(
        status: String,
        pName: String,
        timeorder: String,
        totalitem: String,
        totalprice: String,
        uId: String,
        uName: String,
        image: String
    ) {
        this.status = status
        this.pName = pName
        this.timeorder = timeorder
        this.totalitem = totalitem
        this.totalprice = totalprice
        this.uId = uId
        this.uName = uName
        this.image = image
    }

    constructor() {}

    fun getStatus(): String {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
    }

    fun getpName(): String {
        return pName
    }

    fun setpName(pName: String) {
        this.pName = pName
    }

    fun getTimeorder(): String {
        return timeorder
    }

    fun setTimeorder(timeorder: String) {
        this.timeorder = timeorder
    }

    fun getTotalitem(): String {
        return totalitem
    }

    fun setTotalitem(totalitem: String) {
        this.totalitem = totalitem
    }

    fun getTotalprice(): String {
        return totalprice
    }

    fun setTotalprice(totalprice: String) {
        this.totalprice = totalprice
    }

    fun getuId(): String {
        return uId
    }

    fun setuId(uId: String) {
        this.uId = uId
    }

    fun getuName(): String {
        return uName
    }

    fun setuName(uName: String) {
        this.uName = uName
    }

    fun getImage(): String {
        return image
    }

    fun setImage(image: String) {
        this.image = image
    }
}