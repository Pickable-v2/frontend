package com.example.pickable

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //자식 Activity위임
        val layoutId = getLayoutId()
        setContentView(layoutId)
        setupBottomNavigation()
    }

    abstract fun getLayoutId(): Int

    private fun setupBottomNavigation() {
        // BottomNavigationView와 FloatingActionButton 설정
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val floatingButton: FloatingActionButton = findViewById(R.id.floatingButton)

        // Bottom Navigation 버튼 선택 시 이벤트 처리
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // TODO: 홈 화면으로 전환
                    Log.d("BottomNav", "Home selected")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0) // 애니메이션 제거
                    true
                }
                R.id.nav_search -> {
                    // TODO: 검색 화면으로 전환
                    Log.d("BottomNav", "Search selected")
                    val intent = Intent(this, RestaurantsSearchActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0) // 애니메이션 제거
                    true
                }
                R.id.nav_review -> {
                    // TODO: 리뷰 목록 화면으로 전환
                    Log.d("BottomNav", "Review selected")

                    overridePendingTransition(0, 0) // 애니메이션 제거
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
}