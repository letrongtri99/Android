package com.example.retrofit.Retrofit2

import retrofit2.Retrofit
import retrofit2.create

public class APIUtils{
    public final var base_Ur:String ="http://192.168.1.50/Quanlysinhvien/"
    public lateinit var retrofitClient: RetrofitClient
    public fun getData(): DataClient {
        return retrofitClient.getClient(base_Ur).create(DataClient::class.java)
    }
}