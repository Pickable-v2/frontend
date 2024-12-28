package com.example.pickable

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.TypedValue
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.NumberPicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity4 : AppCompatActivity() {

    private fun customizeNumberPicker(numberPicker: NumberPicker) {
        for (i in 0 until numberPicker.childCount) {
            val child = numberPicker.getChildAt(i)
            if (child is EditText) {
                child.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f) // 텍스트 크기
                child.setTextColor(Color.parseColor("#B4D6CD")) // 텍스트 색상
            }
        }
    }

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
        Log.d("IntentTest-pw", "Button for $nickname $id $pw intent.")
        val nextBtn : Button = findViewById(R.id.nextBtn)
        val backBtn : ImageView = findViewById(R.id.backBtn)

        //NumberPicker
        val numberPicker: NumberPicker = findViewById(R.id.npker)
        numberPicker.minValue = 1
        numberPicker.maxValue = 100
        numberPicker.wrapSelectorWheel = false // 100초과시 반복 X
        customizeNumberPicker(numberPicker)

        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            Log.d("NumberPicker", "Value changed: $newVal")
        }

        numberPicker.setOnScrollListener { picker, scrollState ->
            if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                if (picker.value < picker.minValue) {
                    picker.value = picker.minValue
                } else if (picker.value > picker.maxValue) {
                    picker.value = picker.maxValue
                }
                Log.d("NumberPicker", "Scroll stopped at: ${picker.value}")
            }
        }
        numberPicker.isSaveFromParentEnabled = false
        numberPicker.isSaveEnabled = true

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
                Log.d("DataTest-age", "Button for $id, $nickname, $pw, $selectedAge added.")
                Log.i("DataTest", "닉네임 값: "+nickname+", ID: "+id+", PW: "+pw+", age: "+selectedAge)
            }
        }

        //이전
        backBtn.setOnClickListener {
            onBackPressed()
        }
    }
}