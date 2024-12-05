package com.example.pickable

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up4)
        //시스템 바
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nickname = intent.getStringExtra("nickname")
        val id = intent.getStringExtra("id")
        val pw = intent.getStringExtra("pw")
        val nextBtn : Button = findViewById(R.id.nextBtn)

        //NumberPicker
        val numberPicker: NumberPicker = findViewById(R.id.npker)
        numberPicker.minValue = 1
        numberPicker.maxValue = 100
        numberPicker.wrapSelectorWheel = false // 100초과시 반복 X

        //다음 버튼
        nextBtn.setOnClickListener {
            val selectedAge = numberPicker.value //선택된 나이

            Handler(Looper.getMainLooper()).post {
                val intent = Intent(this, SignUpActivity5::class.java)
                intent.putExtra("nickname", nickname)
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)
                intent.putExtra("age", selectedAge)

                startActivity(intent)
                Log.d("DataTest", "Button for $id, $nickname, $pw, $selectedAge  added.")
            }
        }
    }
}