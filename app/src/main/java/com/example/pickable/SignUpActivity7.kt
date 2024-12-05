package com.example.pickable

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up7)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Intent data 받기
        val nickname = intent.getStringExtra("nickname")
        val id = intent.getStringExtra("id")
        val pw = intent.getStringExtra("pw")
        val age = intent.getIntExtra("AGE", -1)
        val gender = intent.getStringExtra("gender")
        val keywords = intent.getStringExtra("keywords")

        val startBtn : Button = findViewById(R.id.startBtn)

        val endingText2 : TextView = findViewById(R.id.endingText2)
        if(nickname != null){
            endingText2.text = "$nickname 님 환영합니다."
        }

        startBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("nickname", nickname)
            intent.putExtra("id", id)
            intent.putExtra("pw", pw)
            intent.putExtra("age", age)
            intent.putExtra("gender", gender)
            intent.putExtra("keywords", keywords)
            startActivity(intent)
            Log.d("DataTest", "Button for $id, $nickname, $pw, $age,$gender $keywords added.")
        }

    }
}