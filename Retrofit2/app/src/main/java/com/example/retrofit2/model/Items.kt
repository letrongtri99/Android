package com.example.retrofit2.model

import com.google.gson.annotations.SerializedName

class Items (
    @SerializedName("score")
    var score:Int?=0,
    @SerializedName("owner")
    var owner:Owner? = null

)