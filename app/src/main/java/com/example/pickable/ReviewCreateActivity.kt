package com.example.pickable

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.flexbox.FlexboxLayout


class ReviewCreateActivity : AppCompatActivity() {

    private lateinit var imagePickerLauncher: ActivityResultLauncher<String>
    private lateinit var filePickerLauncher: ActivityResultLauncher<String>
    private lateinit var imageUploadView: ImageView

    // 별점 관련 변수 선언
    private lateinit var starViews: List<ImageView>
    private var currentRating = 0

    //키워드 선택
    private val maxSelections = 5 // 해시태그 최대 개수
    private val selectedPreferences = ArrayList<String>() // 선택된 해시태그

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review_create)


        // 이전 화면으로 돌아가기 버튼 설정
        findViewById<ImageView>(R.id.backBtn).setOnClickListener {
            finish() // 현재 액티비티 종료
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.reviewCreate)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Intent로 전달된 데이터 수신
        val restaurantName = intent.getStringExtra("restaurant_name")
        val restaurantAddress = intent.getStringExtra("restaurant_address")
        val restaurantRating = intent.getStringExtra("restaurant_rating")

        // View에 데이터 표시
        findViewById<TextView>(R.id.nameReviewView).text = restaurantName
        findViewById<TextView>(R.id.addressReviewView).text = restaurantAddress
        findViewById<TextView>(R.id.ratingReviewView).text = restaurantRating

        // 영수증 등록 완료 확인
        findViewById<TextView>(R.id.receiptUploadText).visibility = View.GONE

        // 영수증 파일 선택
        filePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                findViewById<TextView>(R.id.receiptUploadText).apply {
                    text = "등록완료!"
                    visibility = View.VISIBLE
                }
            }
        }

        findViewById<Button>(R.id.receiptUploadBtn).setOnClickListener {
            filePickerLauncher.launch("image/*") // 이미지 파일만 선택
        }

        // 갤러리에서 이미지 선택
        imageUploadView = findViewById(R.id.imageUpload)

        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUploadView.setImageURI(it)
                imageUploadView.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }

        imageUploadView.setOnClickListener {
            imagePickerLauncher.launch("image/*") // 이미지 파일만 선택
        }

        // 별점 관련 뷰 초기화
        starViews = listOf(
            findViewById(R.id.star1),
            findViewById(R.id.star2),
            findViewById(R.id.star3),
            findViewById(R.id.star4),
            findViewById(R.id.star5)
        )

        // 기본 별점 색상 설정
        resetStarColors()

        // 각 별에 클릭 리스너 추가
        for ((index, star) in starViews.withIndex()) {
            star.setOnClickListener {
                updateRating(index + 1)
            }
        }

        val buttonContainer = findViewById<FlexboxLayout>(R.id.reviewPrefer)

        // 키워드 data
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
                                this@ReviewCreateActivity,
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

    }

    // 별점 업데이트 함수
    private fun updateRating(rating: Int) {
        currentRating = rating

        // 별 색상 업데이트
        for (i in starViews.indices) {
            if (i < rating) {
                starViews[i].setColorFilter(getColor(R.color.star_filled)) // 채워진 별 색상
            } else {
                starViews[i].setColorFilter(getColor(R.color.star_empty)) // 빈 별 색상
            }
        }

        // 텍스트뷰 업데이트
        findViewById<TextView>(R.id.editRatingText).text = currentRating.toString()
    }

    // 별 초기 색상 설정 함수
    private fun resetStarColors() {
        for (star in starViews) {
            star.setColorFilter(getColor(R.color.star_empty)) // 빈 별 색상
        }
    }
}