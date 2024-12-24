package com.example.pickable

import DBHelper
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.regex.Pattern

class SignUpActivity2 : AppCompatActivity() {
    var DB:DBHelper?=null
    var CheckId:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up2)
        //시스템 바
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nickname = intent.getStringExtra("nickname")
        Log.d("IntentTest", "Button for $nickname  intent.")

        val editId : EditText = findViewById(R.id.editId)
        val nextBtn : Button = findViewById(R.id.nextBtn)
        val idDuplicate : TextView = findViewById(R.id.idDuplicate)

        // DB 객체 초기화
        DB = DBHelper(this)

        //아이디 중복확인
        // 아이디 중복확인
        idDuplicate.setOnClickListener {
            val user = editId.text.toString()
            val idPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,15}$"

            if (user == "") {
                Toast.makeText(
                    this@SignUpActivity2,
                    "아이디를 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                if (Pattern.matches(idPattern, user)) {
                    val checkUsername = DB!!.checkUser(user)
                    if(checkUsername == false){
                        CheckId = true
                        Toast.makeText(this@SignUpActivity2, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this@SignUpActivity2, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(this@SignUpActivity2, "아이디 형식이 옳지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //다음
        nextBtn.setOnClickListener {
            val id = editId.text.toString()
            if(id == ""){
                Toast.makeText(this@SignUpActivity2, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                if(CheckId == true){
                    val intent = Intent(this, SignUpActivity3::class.java)
                    intent.putExtra("nickname", nickname)
                    intent.putExtra("id", id)
                    startActivity(intent)
                    Log.d("DataTest", "Button for $id , $nickname  added.")
                } else{
                    Toast.makeText(
                        this@SignUpActivity2,
                        "아이디 중복확인을 해주세요.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}