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

class SignUpActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up2)
        //시스템 바
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nickname = intent.getStringExtra("nickname")
        val EditId : EditText = findViewById(R.id.EditId)
        val nextBtn : Button = findViewById(R.id.nextBtn)
        val idDuplicate : TextView = findViewById(R.id.idDuplicate)

        //닉네임 중복확인
        idDuplicate.setOnClickListener {

        }

        //다음
        nextBtn.setOnClickListener {
            val id = EditId.text.toString()

            val intent = Intent(this, SignUpActivity3::class.java).apply {
                intent.putExtra("id", id)
            }
            startActivity(intent)
        }
    }
}