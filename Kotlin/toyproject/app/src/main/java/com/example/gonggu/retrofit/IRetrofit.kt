package com.example.gonggu.retrofit

import com.example.gonggu.DTOs.userDTO
import com.example.gonggu.model.readAllDTO
import com.example.gonggu.model.readDTO
import com.example.gonggu.model.writeDTO
import com.example.gonggu.utils.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface IRetrofit {

    @POST(API.SEARCH_USERS)
    fun addUser() : Call<userDTO>

    @GET(API.SEARCH_USERS)
    fun getUser(@Query("query") searchTerm: String) : Call<JsonElement>

    //글등록
    @Headers("Content-Type: application/json")
    @POST("/api/v1/posts")
    fun write(
        @Body
        writeDTO: writeDTO
    ) : Call<writeDTO>

    //전체 글 조회
    @GET("/api/v1/posts")
    fun readAll() : Call<List<readAllDTO>>

    //글 세부 조회
    @GET("/api/v1/posts/{id}")
    fun read(@Path(value="id",encoded=true) id:Int) : Call<readDTO>

    @PUT("/api/v1/posts/{id}")
    fun modify(
        @Path(value="id",encoded=true) id:Int,
        @Body writeDTO: writeDTO) : retrofit2.Call<writeDTO>

    @DELETE("/api/v1/posts/{id}")
    fun delete(@Path(value="id",encoded=true) id:Int): retrofit2.Call<Void>

}

