package com.example.gonggu.model

import com.google.gson.annotations.SerializedName

//전체 글 읽기 dto
data class readAllDTO(
    @SerializedName("createdDate") var createdDate: String?,
    @SerializedName("modifiedDate") var modifiedDate: String?,
    @SerializedName("id") var id: Int?,
    @SerializedName("title") var title: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("link") var link: String?,
    @SerializedName("contact") var contact: String?,
    @SerializedName("price") var price: String?,
    @SerializedName("date") var date: String?,
    @SerializedName("author") var author: String?
)
