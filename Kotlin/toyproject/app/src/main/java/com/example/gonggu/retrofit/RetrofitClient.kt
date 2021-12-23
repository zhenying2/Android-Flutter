package com.example.gonggu.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// 레트로핏 클라이언트 클래스
// 메모리를 하나 사용하는 싱글턴 패턴을 적용해서 어디서든 하나의 객체를 가져온다.
object RetrofitClient {

    val TAG: String = "로그"

    // 레트로핏 클라이언트 선언

    private var retrofitClient: Retrofit? = null


    // 레트로핏 클라이언트 가져오기
    fun getClient(baseUrl: String): Retrofit? {

        // 만약 레트로핏 클라이이언트가 없으면
        if(retrofitClient == null){

            // 인스턴스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        return retrofitClient

    }

}