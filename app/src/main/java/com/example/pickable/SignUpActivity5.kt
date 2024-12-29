package com.example.pickable

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity5 : AppCompatActivity() {
    private var selectedGender : String? = null
    private lateinit var maleBtn : Button
    private lateinit var femaleBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up5)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backBtn : ImageView = findViewById(R.id.backBtn)

        val nickname = intent.getStringExtra("nickname")
        val id = intent.getStringExtra("id")
        val pw = intent.getStringExtra("pw")
        val age = intent.getIntExtra("age",-1)
        Log.d("IntentTest-age", "Button for $nickname $id $pw $age intent.")

        //버튼 초기화
        maleBtn = findViewById(R.id.maleBtn)
        femaleBtn = findViewById(R.id.femaleBtn)
        val nextBtn : Button = findViewById(R.id.nextBtn)

        //SharedPreferences 가져오기
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

        //저장된 gender 불러오기
        selectedGender = sharedPreferences.getString("selectedGender", null)
        restoreGenderState()

        maleBtn.setOnClickListener {
            selectGender("남자", maleBtn, femaleBtn, sharedPreferences)
        }

        femaleBtn.setOnClickListener {
            selectGender("여자", femaleBtn, maleBtn, sharedPreferences)
        }

        //다음
        nextBtn.setOnClickListener {
            if(selectedGender != null) {
                val gender = selectedGender.toString();
                val intent = Intent(this, SignUpActivity6::class.java)
                intent.putExtra("nickname", nickname)
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)
                intent.putExtra("age", age)
                intent.putExtra("gender", gender)

                startActivity(intent)
                Log.d("DataTest-gender", "Button for $id, $nickname, $pw, $age,$gender added.")
                Log.i("DataTest", "닉네임 값: "+nickname+", ID: "+id+", PW: "+pw+", age: "+age+", 성별: "+gender)
            }
        }

        //이전
        backBtn.setOnClickListener {
            onBackPressed()
        }

    }

    private fun selectGender(
        gender: String,
        selectedButton: Button,
        unselectedButton: Button,
        shardPreferences: SharedPreferences
    ) {
        //선택 gender 저장
        selectedGender = gender

        //SharedPreferences에 저장
        shardPreferences.edit().putString("selectedGender", gender).apply()

        //버튼 상태 변경
        if(gender == "남자") {
            selectedButton.setBackgroundResource(R.drawable.btn_selected_male)
            unselectedButton.setBackgroundResource(R.drawable.btn_unselected)
        } else if(gender == "여자"){
            selectedButton.setBackgroundResource(R.drawable.btn_selected_female)
            unselectedButton.setBackgroundResource(R.drawable.btn_unselected)
        }
    }

    private fun restoreGenderState() {
        //이전 선택 gender별 버튼 상태 복원
        when(selectedGender){
            "남자" -> {
                maleBtn.setBackgroundResource(R.drawable.btn_selected_male)
                femaleBtn.setBackgroundResource(R.drawable.btn_unselected)
            }
            "여자" -> {
                femaleBtn.setBackgroundResource(R.drawable.btn_selected_female)
                maleBtn.setBackgroundResource(R.drawable.btn_unselected)
            }
        }
    }
}