package com.example.gonggu

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.gonggu.model.readDTO
import com.example.gonggu.model.writeDTO
import com.example.gonggu.retrofit.IRetrofit
import com.example.gonggu.retrofit.RetrofitClient
import com.example.gonggu.utils.API
import retrofit2.Call
import retrofit2.Response

class MyDetailPostView: AppCompatActivity() {
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

        setContentView(R.layout.fragment_my_self_post_view)

        var modified_btn=findViewById<AppCompatButton>(R.id.modified_btn)
        var deleted_btn=findViewById<AppCompatButton>(R.id.del_btn)

        //수정하기 버튼을 눌렀을 때
        modified_btn.setOnClickListener {

            //id값 매칭 & 현재의 입력값 받아서 -> writeDTO 다시 만들기
            var title=findViewById<EditText>(R.id.et_post_title).text.toString() //글제목
            var author=findViewById<TextView>(R.id.et_post_writer).text.toString()  //글쓴이
            var price=findViewById<EditText>(R.id.et_price).text.toString()  //공구가격
            var period=findViewById<EditText>(R.id.et_datess).text.toString()  //공구 날짜
            var link=findViewById<EditText>(R.id.et_product_link).text.toString()  //공구링크
            var contact=findViewById<EditText>(R.id.et_messanger_link).text.toString()  //오픈채팅 링크
            var description=findViewById<EditText>(R.id.et_content).text.toString()  //글내용

            var new_DTO= writeDTO(title, description, link, contact, price, period, author)

            //수정하기
            var call = httpCall?.modify(id=id_extra,new_DTO)
            call?.enqueue(object : retrofit2.Callback<writeDTO>{
                override fun onResponse(call: Call<writeDTO>, response: Response<writeDTO>) {
                    Log.d(TAG, "RetrofitManager - getTodo() - onResponse() called / response: $response")
                    Log.d(TAG, "response.body : ${response.body()}")
                    Toast.makeText(baseContext,"수정완료!", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<writeDTO>, t: Throwable) {
                    Log.d(TAG, "RetrofitManager - getTodo() - onFailure() called / t: ${t}")
                }
            })


        }
        //삭제하기 버튼을 눌렀을 때
        deleted_btn.setOnClickListener {

            //삭제하기
            var call = httpCall?.delete(id=id_extra)
            call?.enqueue(object : retrofit2.Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d(TAG, "RetrofitManager - getTodo() - onResponse() called / response: $response")
                    Log.d(TAG, "response.body : ${response.body()}")
                    Toast.makeText(baseContext,"삭제 완료!", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d(TAG, "RetrofitManager - getTodo() - onFailure() called / t: ${t}")
                }
            })

        }
    }
}