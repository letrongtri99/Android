package com.example.retrofit2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit2.model.service.AnswerResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webservice = ApiUtils.webservice
        webservice.getAnswers().enqueue(object : Callback<AnswerResponse> {
            override fun onResponse(call: Call<AnswerResponse>, response: Response<AnswerResponse>) {

                if (response.isSuccessful()) {
                    val soAnswerResponse = response.body()
                    var a: Int = 0
                    for (item in soAnswerResponse.items!!) {
                        a += item.score!!
                    }
//                    mAdapter.updateAnswers(response.body().getItems())
//                    Log.d("MainActivity", "posts loaded from API")
                    txtList.setText(a.toString())

                }
            }

            override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
//                showErrorMessage()
                Log.d("MainActivity", "error loading from API")

            }
        })
    }
}
