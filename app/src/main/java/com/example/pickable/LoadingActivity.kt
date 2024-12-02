package com.example.pickable

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoadingActivity : AppCompatActivity() {
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

        val inputId : EditText = findViewById(R.id.inputId)
        val inputPw : EditText = findViewById(R.id.inputPw)
        val loginBtn : Button = findViewById(R.id.loginBtn) //로그인 버튼
        val findPwBtn : TextView = findViewById(R.id.findPwBtn) //비번 찾기
        val signUpBtn : TextView = findViewById(R.id.signUpBtn) // 회원가입

        //로그인
        loginBtn.setOnClickListener {
            val id = inputId.text.toString()
            val pw = inputPw.text.toString()
            val intent = Intent(this, MainActivity::class.java).apply {
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)
            }
            startActivity(intent)
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