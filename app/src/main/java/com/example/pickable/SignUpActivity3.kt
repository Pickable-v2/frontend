package com.example.pickable

import DBHelper
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.regex.Pattern

class SignUpActivity3 : AppCompatActivity() {
    var DB:DBHelper?=null
    var CheckPw:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up3)
        //시스템 바
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nickname = intent.getStringExtra("nickname")
        val id = intent.getStringExtra("id")
        val editPassWord : EditText = findViewById(R.id.editPassWord)
        val editPassWord2 : EditText = findViewById(R.id.editPassWord2)
        val nextBtn : Button = findViewById(R.id.nextBtn)
        val checkPW : TextView = findViewById(R.id.checkPW)

        // DB 객체 초기화
        DB = DBHelper(this)

        //비밀번호 보기
        checkPW.setOnClickListener {

        }

        //다음
        nextBtn.setOnClickListener {
            //editPassWord와 editPassWord2가 일치하면
            val beforePw = editPassWord.text.toString()
            val afterPw = editPassWord2.text.toString()
            if(beforePw == "" || afterPw == ""){
                Toast.makeText(this@SignUpActivity3, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else{
                if(beforePw == afterPw){
                    val intent = Intent(this, SignUpActivity4::class.java)
                    intent.putExtra("nickname", nickname)
                    intent.putExtra("id", id)
                    intent.putExtra("pw", afterPw)
                    startActivity(intent)
                    Log.d("DataTest", "Button for $id, $nickname, $afterPw added.")
                } else {
                    Toast.makeText(this@SignUpActivity3, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}