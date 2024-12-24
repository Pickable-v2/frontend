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

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main // MainActivity의 레이아웃 리소스
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
