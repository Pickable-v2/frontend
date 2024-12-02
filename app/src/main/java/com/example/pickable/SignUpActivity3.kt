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

class SignUpActivity3 : AppCompatActivity() {
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

        val editPassWord : EditText = findViewById(R.id.editPassWord)
        val editPassWord2 : EditText = findViewById(R.id.editPassWord2)
        val nextBtn : Button = findViewById(R.id.nextBtn)
        val checkPW : TextView = findViewById(R.id.checkPW)

        //비밀번호 보기
        checkPW.setOnClickListener {

        }

        //다음
        nextBtn.setOnClickListener {
            //editPassWord와 editPassWord2가 일치하면
            val pw = editPassWord2.text.toString()

            val intent = Intent(this, SignUpActivity4::class.java).apply {
                intent.putExtra("id", pw)
            }
            startActivity(intent)
        }
    }
}