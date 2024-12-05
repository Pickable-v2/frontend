package com.example.pickable

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val dateText : TextView = findViewById(R.id.dateText)
        val weekText : TextView = findViewById(R.id.weekText)
        val mypageBtn : ImageView = findViewById(R.id.mypageBtn)

        val currentDate = LocalDate.now() // 현재 날짜 가져오기
        val dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일")
        dateText.text = currentDate.format(dateFormatter)

        weekText.text = doDayOfWeek()

        mypageBtn.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }
    }
}

private fun doDayOfWeek(): String? {
    val cal: Calendar = Calendar.getInstance()
    var strWeek: String? = null
    val nWeek: Int = cal.get(Calendar.DAY_OF_WEEK)

    if (nWeek == 1) {
        strWeek = "일요일"
    } else if (nWeek == 2) {
        strWeek = "월요일"
    } else if (nWeek == 3) {
        strWeek = "화요일"
    } else if (nWeek == 4) {
        strWeek = "수요일"
    } else if (nWeek == 5) {
        strWeek = "목요일"
    } else if (nWeek == 6) {
        strWeek = "금요일"
    } else if (nWeek == 7) {
        strWeek = "토요일"
    }
    return strWeek
}