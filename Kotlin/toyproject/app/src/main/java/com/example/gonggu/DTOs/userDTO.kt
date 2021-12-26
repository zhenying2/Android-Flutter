package com.example.gonggu.DTOs

import com.google.gson.annotations.SerializedName

data class userDTO(
    @SerializedName("email") var email : String? = null,
    @SerializedName("name") var name : String? = null,
    @SerializedName("picture") var picture : String? = null,
)
