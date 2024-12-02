package com.example.pickable

import android.content.Intent
import android.os.Bundle
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

        //NumberPicker
        val numberPicker: NumberPicker = findViewById(R.id.npker)
        numberPicker.minValue = 1
        numberPicker.maxValue = 100
        numberPicker.wrapSelectorWheel = false // 100초과시 반복 X

        //다음 버튼
        val nextbtn : Button = findViewById(R.id.nextBtn)
        nextbtn.setOnClickListener {
            val selectedAge = numberPicker.value //선택된 나이

            val intent = Intent(this, SignUpActivity5::class.java).apply {
                putExtra("AGE", selectedAge) // 선택 나이 전달
            }
            startActivity(intent)
        }
    }
}