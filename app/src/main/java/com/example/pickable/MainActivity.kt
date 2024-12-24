package com.example.pickable

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 날짜와 요일 설정
        val dateText: TextView = findViewById(R.id.dateText)
        val weekText: TextView = findViewById(R.id.weekText)
        val mypageBtn: ImageView = findViewById(R.id.mypageBtn)

        val currentDate = LocalDate.now() // 현재 날짜 가져오기
        val dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일")
        dateText.text = currentDate.format(dateFormatter)
        weekText.text = doDayOfWeek()

        // 마이페이지 버튼 클릭 리스너
        mypageBtn.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        // BottomNavigationView와 FloatingActionButton 설정
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val floatingButton: FloatingActionButton = findViewById(R.id.floatingButton)

        // Bottom Navigation 버튼 선택 시 이벤트 처리
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // TODO: 홈 화면으로 전환
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_search -> {
                    // TODO: 검색 화면으로 전환
                    val intent = Intent(this, RestaurantsSearchActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_review -> {
                    // TODO: 리뷰 화면으로 전환
                    true
                }
                R.id.nav_rank -> {
                    // TODO: 랭킹 화면으로 전환
                    true
                }
                else -> false
            }
        }

        // FloatingActionButton 클릭 이벤트
        floatingButton.setOnClickListener {
            // TODO: 채팅 페이지로 이동
        }
    }

    // 요일 계산 함수
    private fun doDayOfWeek(): String? {
        val cal: Calendar = Calendar.getInstance()
        val nWeek: Int = cal.get(Calendar.DAY_OF_WEEK)

        return when (nWeek) {
            Calendar.SUNDAY -> "일요일"
            Calendar.MONDAY -> "월요일"
            Calendar.TUESDAY -> "화요일"
            Calendar.WEDNESDAY -> "수요일"
            Calendar.THURSDAY -> "목요일"
            Calendar.FRIDAY -> "금요일"
            Calendar.SATURDAY -> "토요일"
            else -> null
        }
    }
}
