package com.example.retrofit.Retrofit2

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Part

public interface DataClient{
    @Multipart
    @POST("uploadhinhanh.php")
    public fun uploadPhoto(@Part photo: MultipartBody.Part):Call<String>
}