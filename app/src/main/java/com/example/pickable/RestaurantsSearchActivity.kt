package com.example.pickable

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

data class Restaurant(val name: String, val address: String, val rating: Float, val category: String)

class RestaurantsSearchActivity : AppCompatActivity() {
    private lateinit var restaurantAdapter: RestaurantListAdapter
    private lateinit var restaurantList: List<Restaurant>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants_serach)

        // View 참조
        val spinner: Spinner = findViewById(R.id.spinner)
        val listView: ListView = findViewById(R.id.restaurantListView)
        val searchView: SearchView = findViewById(R.id.searchView)
        val mypageBtn : ImageView = findViewById(R.id.mypageBtn)

        // Spinner 데이터 설정
        val sortOptions = listOf("전체", "거리순")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sortOptions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        // 맛집 데이터 설정
        restaurantList = listOf(
            Restaurant("카푸베어", "서울 광진구 능동로 143 지하1층", 3.5f, "카페"),
            Restaurant("호야초밥참치 본점", "서울 광진구 능동로 13길 39 한아름건물 1층", 5.0f, "초밥"),
            Restaurant("등불", "서울 성동구 아차산로7길 4", 4.0f, "갈비"),
            Restaurant("쪼쪼", "서울 성동구 연무장17길 71층", 4.5f, "일식")
        )

        // 커스텀 어댑터 설정
        restaurantAdapter = RestaurantListAdapter(this, restaurantList)
        listView.adapter = restaurantAdapter

        // 검색 필터링 구현
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // 이름 또는 카테고리로 필터링
                val filteredList = restaurantList.filter {
                    it.name.contains(newText ?: "", ignoreCase = true) ||
                            it.category.contains(newText ?: "", ignoreCase = true)
                }
                restaurantAdapter.updateData(filteredList)
                return true
            }
        })

        mypageBtn.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        // 정렬 기능 (Spinner 사용)
//        spinner.setOnItemSelectedListener { parent, view, position, id ->
//            when (position) {
//                0 -> { // "전체" 선택 시 원래 순서 유지
//                    restaurantAdapter.updateData(restaurantList)
//                }
//                1 -> { // "거리순" 선택 시 임의의 거리 기준으로 정렬 (예: rating으로 정렬)
//                    val sortedList = restaurantList.sortedByDescending { it.rating }
//                    restaurantAdapter.updateData(sortedList)
//                }
//            }
//        }
    }
}
