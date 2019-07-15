package com.example.retrofit2

import com.example.retrofit2.model.service.AnswerResponse
import retrofit2.Call;
import retrofit2.http.GET;

interface SOService {

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    fun getAnswers():Call<AnswerResponse>
}