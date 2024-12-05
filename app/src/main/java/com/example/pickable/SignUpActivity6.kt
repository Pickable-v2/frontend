package com.example.pickable

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.flexbox.FlexboxLayout

class SignUpActivity6 : AppCompatActivity() {
    private val maxSelections = 5 // 해시태그 최대 개수
    private val selectedPreferences = mutableListOf<String>() // 선택된 해시태그

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up6)

        val buttonContainer = findViewById<FlexboxLayout>(R.id.buttonContainer)
        val nextBtn: Button = findViewById(R.id.nextBtn)

        nextBtn.isEnabled = true

        // Temporary data
        val preferences = listOf(
            "달콤한", "초딩입맛", "맵찔이", "매콤한", "기름진",
            "달달한", "얼큰한", "엄마손맛", "크리미한", "산뜻한",
            "한식러버", "바다향기"
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
                    if (selectedPreferences.contains(preference)) {
                        selectedPreferences.remove(preference) // 선택 해제
                        setTextColor(Color.BLACK)
                        background = GradientDrawable().apply {
                            setColor(Color.WHITE) // 기본 배경색
                            cornerRadius = 80f
                        }
                    } else {
                        if (selectedPreferences.size < maxSelections) {
                            selectedPreferences.add(preference) // 선택
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

        // Next 버튼 클릭 이벤트
        nextBtn.setOnClickListener {
            if (nextBtn.isEnabled && selectedPreferences.size == maxSelections) {
                val intent = Intent(this, SignUpActivity7::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@SignUpActivity6, "5개를 모두 선택해주세요", Toast.LENGTH_SHORT).show()
                Log.d("ButtonTest", "Toast shown for insufficient selection.")
            }
        }
    }
}
