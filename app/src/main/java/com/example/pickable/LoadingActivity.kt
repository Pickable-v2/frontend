package com.example.pickable

import DBHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoadingActivity : AppCompatActivity() {
    var DB : DBHelper?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loading)
        //시스템 바
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        DB = DBHelper(this)

        val inputId : EditText = findViewById(R.id.inputId)
        val inputPw : EditText = findViewById(R.id.inputPw)
        val loginBtn : Button = findViewById(R.id.loginBtn) //로그인 버튼
        val findPwBtn : TextView = findViewById(R.id.findPwBtn) //비번 찾기
        val signUpBtn : TextView = findViewById(R.id.signUpBtn) // 회원가입

        //로그인
        loginBtn.setOnClickListener {
            val id = inputId.text.toString()
            val pw = inputPw.text.toString()

            //빈칸 제출시 알림창
            if(id == "" || pw == ""){
                Toast.makeText(this@LoadingActivity, "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else {
                val checkUserpass = DB!!.checkUserpass(id, pw)
                // id 와 password 일치시
                if (checkUserpass == true) {
                    Toast.makeText(this@LoadingActivity, "로그인 되었습니다.", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this@LoadingActivity, "아이디와 비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 비밀번호 찾기
        findPwBtn.setOnClickListener {

        }

        //회원가입
        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}