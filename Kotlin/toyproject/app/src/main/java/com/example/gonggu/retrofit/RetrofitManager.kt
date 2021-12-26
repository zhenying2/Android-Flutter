package com.example.gonggu.retrofit

import android.util.Log
import com.example.gonggu.DTOs.userDTO
import com.example.gonggu.model.readAllDTO
import com.example.gonggu.model.readDTO
import com.example.gonggu.model.writeDTO
import com.example.gonggu.utils.API
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {
    val TAG : String = "로그"

    companion object {
        val instance = RetrofitManager()
    }

    // 레트로핏 인터페이스 가져오기
    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)
    private val httpCall : IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //회원 추가
    fun addUsers(userDTO: userDTO){
        var call = httpCall?.addUsers(userDTO)
        call?.enqueue(object : retrofit2.Callback<userDTO>{
            override fun onResponse(call: Call<userDTO>, response: Response<userDTO>) {
                Log.d(TAG, "성공했다는 뜻 RetrofitManager - onResponse() called / response : $response ")
                Log.d(TAG, "response.body : ${response.body()}")
            }

            override fun onFailure(call: Call<userDTO>, t: Throwable) {
                Log.d(TAG, "실패햇슈 실패햇슈 RetrofitManager - onFailure() called / t: $t ")
            }

        })
    }

    // 유저 검색 api 호출
    fun searchUser(searchTerm: String?, completion: (String) -> Unit) {

        val term = searchTerm.let { it }?: ""

        val call = iRetrofit?.getUser(searchTerm = term).let {

        }?: return
    }


    //글등록
    fun write(writeDTO: writeDTO){
        var call = httpCall?.write(writeDTO)
        call?.enqueue(object : retrofit2.Callback<writeDTO>{
            override fun onResponse(call: Call<writeDTO>, response: Response<writeDTO>) {
                Log.d(TAG, "RetrofitManager - getTodo() - onResponse() called / response: $response")
                Log.d(TAG, "response.body : ${response.body()}")
                Log.d("success", "성공성공")
            }

            override fun onFailure(call: Call<writeDTO>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - getTodo() - onFailure() called / t: ${t}")
                Log.d("fail", "실패실패")
            }
        })
    }

}
