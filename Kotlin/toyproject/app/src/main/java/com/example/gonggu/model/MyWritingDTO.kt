package com.example.gonggu.model

import com.google.gson.annotations.SerializedName

//내가 쓴 글 전체 DTO -> 수정해야함(임시)
data class MyWritingDTO(
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