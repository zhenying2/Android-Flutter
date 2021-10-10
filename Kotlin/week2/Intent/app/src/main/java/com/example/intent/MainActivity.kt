package com.example.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_a.setOnClickListener{
            val intent = Intent(this,SubActivity::class.java) //다음 화면으로 이동하기 위한 인텐트 객체 생성
            intent.putExtra("msg",tv_sendMsg.text.toString())
            startActivity(intent)
        }
    }
}