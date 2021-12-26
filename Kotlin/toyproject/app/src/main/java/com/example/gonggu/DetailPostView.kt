package com.example.gonggu

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gonggu.model.readAllDTO
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

        intent = getIntent();// 인텐트 받아오기
        var id_extra = intent.getIntExtra("id_extra",10); //Adapter에서 받은 키값 연결

        //세부 글 읽기
        var call = httpCall?.readAll()
        call?.enqueue(object : retrofit2.Callback<List<readAllDTO>>{
            override fun onResponse(call: Call<List<readAllDTO>>, response: Response<List<readAllDTO>>) {
                Log.d(TAG, "RetrofitManager - getTodo() - onResponse() called / response: $response")

                //id값 매칭
                var title=findViewById<TextView>(R.id.tv_post_title) //글제목
                var author=findViewById<TextView>(R.id.tv_post_writer) //글쓴이
                var price=findViewById<TextView>(R.id.tv_price) //공구가격
                var period=findViewById<TextView>(R.id.tv_datess) //공구 날짜
                var link=findViewById<TextView>(R.id.tv_product_link) //공구링크
                var contact=findViewById<TextView>(R.id.tv_messanger_link) //오픈채팅 링크
                var description=findViewById<TextView>(R.id.tv_content) //글내용

                //전달받은 position의 정보 받아서 연결
                /*
                author.setText(Gson().toJson(response.body()?.author))
                title.setText(response.body()?.title)
                link.setText(response.body()?.link)
                price.setText(response.body()?.price)
                period.setText(response.body()?.date)
                contact.setText(response.body()?.contact)
                description.setText(response.body()?.description)


                 */

                title.setText(Gson().toJson(response.body()?.get(id_extra)?.title).toString())
                author.setText(Gson().toJson(response.body()?.get(id_extra)?.author).toString())
                link.setText(Gson().toJson(response.body()?.get(id_extra)?.link).toString())
                price.setText(Gson().toJson(response.body()?.get(id_extra)?.price).toString())
                period.setText(Gson().toJson(response.body()?.get(id_extra)?.date).toString())
                contact.setText(Gson().toJson(response.body()?.get(id_extra)?.contact).toString())
                description.setText(Gson().toJson(response.body()?.get(id_extra)?.description).toString())
            }

            override fun onFailure(call: Call<List<readAllDTO>>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - getTodo() - onFailure() called / t: ${t}")

            }
        })

        setContentView(R.layout.fragment_detail_post_view)
    }
}