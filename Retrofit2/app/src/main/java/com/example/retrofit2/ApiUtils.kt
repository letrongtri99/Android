package com.example.retrofit2

object ApiUtils{
    val BASE_URL = "https://api.stackexchange.com/2.2/"
    val webservice: SOService = RetrofitClient.getClient(BASE_URL).create<SOService>(SOService::class.java)
    //khoi tao webservice
}