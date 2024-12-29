package com.example.pickable

import DBHelper
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var DB:DBHelper?=null
        var CheckNick:Boolean=false

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        //시스템 바
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val EditNickName : EditText = findViewById(R.id.editNickName)
        val nextBtn : Button = findViewById(R.id.nextBtn)
        val nickDuplicate : TextView = findViewById(R.id.nickDuplicate)
        val backBtn : ImageView = findViewById(R.id.backBtn)

        // DB 객체 초기화
        DB = DBHelper(this)

        // 닉네임 중복확인
        nickDuplicate.setOnClickListener {
            val nick = EditNickName.text.toString().trim()
            val nickPattern = "^[a-zA-Z0-9가-힣ㄱ-ㅣ]*$"

            if (nick == "") {
                Toast.makeText(
                    this@SignUpActivity,
                    "닉네임을 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                if (Pattern.matches(nickPattern, nick)) {
                    val checkNick = DB.checkNick(nick)
                    if(checkNick == false){
                        CheckNick = true
                        Toast.makeText(this@SignUpActivity, "사용 가능한 닉네임입니다.", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this@SignUpActivity, "이미 존재하는 닉네임입니다.", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(this@SignUpActivity, "닉네임 형식이 옳지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //다음
        nextBtn.setOnClickListener {
            val nickname = EditNickName.text.toString()
            if(nickname == ""){
                Toast.makeText(this@SignUpActivity, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                if(CheckNick == true){
                    val intent = Intent(this, SignUpActivity2::class.java)
                    intent.putExtra("nickname", nickname)
                    startActivity(intent)
                    Log.i("DataTest-nickname", "닉네임 값: "+nickname)
                } else{
                    Toast.makeText(
                        this@SignUpActivity,
                        "닉네임 중복확인을 해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

        //이전
        backBtn.setOnClickListener {
//            val intent = Intent(this, LoadingActivity::class.java)
//            startActivity(intent)
            onBackPressed()
        }
    }
}