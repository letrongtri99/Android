package com.example.retrofit2.model.service

import com.example.retrofit2.model.Items
import com.google.gson.annotations.SerializedName

class AnswerResponse {
    @SerializedName("items")
    var items:List<Items>?= null
}
