package com.example.pickable

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.flexbox.FlexboxLayout

class SignUpActivity6 : AppCompatActivity() {
    private val maxSelections = 5 // 해시태그 최대 개수
    private val selectedPreferences = ArrayList<String>() // 선택된 해시태그

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up6)

        val nickname = intent.getStringExtra("nickname")
        val id = intent.getStringExtra("id")
        val pw = intent.getStringExtra("pw")
        val age = intent.getIntExtra("age", -1)
        val gender = intent.getStringExtra("gender")
        Log.d("IntentTest-gender", "Button for $nickname $id $pw $age $gender intent.")

        val buttonContainer = findViewById<FlexboxLayout>(R.id.buttonContainer)
        val nextBtn: Button = findViewById(R.id.nextBtn)
        val backBtn : ImageView = findViewById(R.id.backBtn)

        nextBtn.isEnabled = true

        // Temporary data
        val preferences = listOf(
            "달콤한", "초딩입맛", "맵찔이", "매콤한", "기름진",
            "달달한", "얼큰한", "엄마손맛", "크리미한", "산뜻한",
            "한식러버", "바다향기"
        )

        // 키워드와 매핑된 값 정의
        val keywordMappings = mapOf(
            "달콤한" to "SWEET",
            "초딩입맛" to "CHILD_TASTE",
            "맵찔이" to "NOT_SPICY",
            "매콤한" to "SPICY",
            "기름진" to "OILY",
            "달달한" to "VERY_SWEET",
            "얼큰한" to "HOT",
            "엄마손맛" to "HOME_MADE",
            "크리미한" to "CREAMY",
            "산뜻한" to "REFRESHING",
            "한식러버" to "KOREAN_FOOD_LOVER",
            "바다향기" to "SEA_FLAVOR"
        )

        // 해시태그 버튼 생성
        for ((index, preference) in preferences.withIndex()) {
            val button = Button(this).apply {
                text = "#$preference"
                textSize = 14f
                setPadding(10, 10, 10, 10) // 버튼 내부 여백
                setTextColor(Color.BLACK) // 기본 텍스트 색상

                // 버튼 배경 설정 (둥근 테두리)
                background = GradientDrawable().apply {
                    setColor(Color.WHITE) // 기본 배경색
                    cornerRadius = 80f // 둥근 테두리 반경
                }
                layoutParams = FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    // 한 줄에 2개와 3개를 번갈아 배치
                    flexBasisPercent = if (index % 5 < 2) 0.5f else 0.33f
                    setMargins(10, 10, 10, 10) // 버튼 간 여백
                }

                setOnClickListener {
                    val mappedValue = keywordMappings[preference] ?: preference // 매핑된 값
                    if (selectedPreferences.contains(mappedValue)) {
                        selectedPreferences.remove(mappedValue) // 선택 해제
                        setTextColor(Color.BLACK)
                        background = GradientDrawable().apply {
                            setColor(Color.WHITE) // 기본 배경색
                            cornerRadius = 80f
                        }
                    } else {
                        if (selectedPreferences.size < maxSelections) {
                            selectedPreferences.add(mappedValue) // 매핑된 값 저장
                            setTextColor(Color.WHITE)
                            background = GradientDrawable().apply {
                                setColor(Color.parseColor("#FF8C9E")) // 선택된 배경색
                                cornerRadius = 80f
                            }
                        } else {
                            Toast.makeText(
                                this@SignUpActivity6,
                                "최대 $maxSelections 개까지 선택 가능합니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    Log.d("ButtonTest", "Selected preferences: $selectedPreferences")
                }
            }
            buttonContainer.addView(button)
            Log.d("ButtonTest", "Button for $preference added.")
        }

        // 다음
        nextBtn.setOnClickListener {
            if (nextBtn.isEnabled && selectedPreferences.size == maxSelections) {
                val intent = Intent(this, SignUpActivity7::class.java)
                intent.putExtra("nickname", nickname)
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)
                intent.putExtra("age", age)
                intent.putExtra("gender", gender)
                intent.putExtra("preferences", ArrayList(selectedPreferences))

                startActivity(intent)
                Log.d("DataTest", "Button for $id, $nickname, $pw, $age,$gender $selectedPreferences  added.")
            } else {
                Toast.makeText(this@SignUpActivity6, "5개를 모두 선택해주세요", Toast.LENGTH_SHORT).show()
                Log.d("ButtonTest", "Toast shown for insufficient selection.")
            }
        }

        //이전
        backBtn.setOnClickListener {
            onBackPressed()
        }
    }
}
