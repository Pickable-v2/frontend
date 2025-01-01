package com.example.pickable

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MyPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_my_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Intent data 받기
        val nickname = intent.getStringExtra("nickname")
        val id = intent.getStringExtra("id")
        val pw = intent.getStringExtra("pw")
        val age = intent.getIntExtra("age", -1)
        val gender = intent.getStringExtra("gender")
        val preferences = intent.getStringArrayListExtra("preferences")


        val nicknameText : TextView = findViewById(R.id.nicknameText)
        val preferText : TextView = findViewById(R.id.preferText)
        if(nickname != null){
            nicknameText.text = nickname
        }
1
        val keywordMappings = mapOf(
             "SWEET" to "#달콤한",
             "CHILD_TASTE" to "#초딩입맛",
             "NOT_SPICY" to "#맵찔이",
             "SPICY" to "#매콤한",
             "OILY" to "#기름진",
             "VERY_SWEET" to "#달달한",
             "HOT" to "#얼큰한",
             "HOME_MADE" to "#엄마손맛",
             "CREAMY" to "#크리미한",
             "REFRESHING" to "#산뜻한",
             "KOREAN_FOOD_LOVER" to "#한식러버",
             "SEA_FLAVOR" to "#바다향기"
        )

        // 받은 preferences를 한글 키워드로 변환
        if (preferences != null) {
            val translatedPreferences = preferences.mapNotNull { keywordMappings[it] }
            preferText.text = translatedPreferences.joinToString(" ")
        }
    }
}