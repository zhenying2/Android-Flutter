package com.example.gonggu.retrofit

import com.example.gonggu.DTOs.userDTO
import com.example.gonggu.model.MyWritingDTO
import com.example.gonggu.model.readAllDTO
import com.example.gonggu.model.readDTO
import com.example.gonggu.model.writeDTO
import com.example.gonggu.utils.API
import com.example.gonggu.utils.API.POST_USERS
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

interface IRetrofit {

    @Headers("Content-Type: application/json")
    @POST(POST_USERS)
    fun addUsers(
        @Body
        userDTO: userDTO
    ) : Call<userDTO>
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
    fun read(@Query("id") id:Int) : Call<readDTO>

    //글수정
    @PUT("/api/v1/posts/{id}")
    fun modify(
        @Path(value="id",encoded=true) id:Int,
        @Body writeDTO: writeDTO) : Call<writeDTO>

    //글삭제
    @DELETE("/api/v1/posts/{id}")
    fun delete(@Path(value="id",encoded=true) id:Int): Call<Void>

    //내가 쓴 글 전체 조회 (수정해야함 api 주소)
    @GET("/api/v1/posts/my/{author}")
    fun MyWriting(@Query("author") author:String) : Call<List<MyWritingDTO>>


}
