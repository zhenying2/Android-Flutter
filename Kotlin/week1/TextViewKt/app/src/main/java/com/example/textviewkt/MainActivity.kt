package com.example.textviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {    //앱이 최초 실행되었을 때 수행
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //xml 화면 view를 연결한다.

        btn_getText.setOnClickListener{ //edit text에 입력되어 있는 값을 가지고 와서 텍스트 뷰에 보여준다.
            var resultText=edit_id.text.toString() //edit text에 입력되어 있는 값
            tv_result.setText(resultText) //입력된 값을 텍스트뷰에 set해줌

        }
    }
}