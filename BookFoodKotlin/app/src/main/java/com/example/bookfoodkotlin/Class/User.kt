package com.example.bookfoodkotlin.Class

public class User {
    internal lateinit var password: String
    internal lateinit var uId: String
    internal lateinit var uName: String

    constructor(password: String, uId: String, uName: String) {
        this.password = password
        this.uId = uId
        this.uName = uName
    }

    constructor() {}

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

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }
}