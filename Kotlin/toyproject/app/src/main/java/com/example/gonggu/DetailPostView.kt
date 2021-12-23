package com.example.gonggu

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gonggu.model.readDTO
import com.example.gonggu.retrofit.IRetrofit
import com.example.gonggu.retrofit.RetrofitClient
import com.example.gonggu.utils.API
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class DetailPostView : AppCompatActivity() {
    private val httpCall : IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)
    val TAG : String = "로그"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        var id_extra=intent.getIntExtra("id_extra",0)

        //세부 글 읽기
        var call = httpCall?.read(id=id_extra)
        call?.enqueue(object : retrofit2.Callback<readDTO>{
            override fun onResponse(call: Call<readDTO>, response: Response<readDTO>) {
                Log.d(TAG, "RetrofitManager - getTodo() - onResponse() called / response: $response")
                Log.d(TAG, "response.body : ${response.body()}")

                //id값 매칭
                var title=findViewById<TextView>(R.id.tv_post_title) //글제목
                var author=findViewById<TextView>(R.id.tv_post_writer) //글쓴이
                var price=findViewById<TextView>(R.id.tv_price) //공구가격
                var period=findViewById<TextView>(R.id.tv_datess) //공구 날짜
                var link=findViewById<TextView>(R.id.tv_product_link) //공구링크
                var contact=findViewById<TextView>(R.id.tv_messanger_link) //오픈채팅 링크
                var description=findViewById<TextView>(R.id.tv_content) //글내용

                //전달받은 position의 정보 받아서 연결
                author.setText(response.body()?.author)
                title.setText(response.body()?.title)
                link.setText(response.body()?.link)
                price.setText(response.body()?.price)
                period.setText(response.body()?.date)
                contact.setText(response.body()?.contact)
                description.setText(response.body()?.description)


            }

            override fun onFailure(call: Call<readDTO>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - getTodo() - onFailure() called / t: ${t}")

            }
        })

        setContentView(R.layout.fragment_detail_post_view)
    }
}