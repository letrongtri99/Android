package com.example.retrofit2.model

import com.google.gson.annotations.SerializedName

class Owner(
    @SerializedName("reputation")
    var reputation:Int?=0,
    @SerializedName("user_id")
    var user_id:Int?=0,
    @SerializedName("user_type")
    var user_type:String?=null
)