package com.example.pickable

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.BaseAdapter

class RestaurantListAdapter(
    private val context: Context,
    private var data: List<Restaurant>

) : BaseAdapter() {

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Any = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_restaurant, parent, false)

        // View 참조
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val addressTextView: TextView = view.findViewById(R.id.addressTextView)
        val categoryTextView: TextView = view.findViewById(R.id.categoryTextView)
        val ratingTextView: TextView = view.findViewById(R.id.rating)
        val reviewButton: Button = view.findViewById(R.id.reviewButton)
        val starIcon: ImageView = view.findViewById(R.id.starIcon)

        // 현재 데이터 설정
        val restaurant = data[position]
        nameTextView.text = restaurant.name
        addressTextView.text = restaurant.address
        categoryTextView.text = restaurant.category
        ratingTextView.text = restaurant.rating.toString()

        // 리뷰쓰기 버튼 동작 설정
        reviewButton.setOnClickListener {
            val intent = Intent(context, ReviewCreateActivity::class.java).apply {
                // 리뷰 작성 페이지 데이터 전달
                putExtra("restaurant_name", restaurant.name)
                putExtra("restaurant_address", restaurant.address)
                putExtra("restaurant_rating", restaurant.rating.toString()) // String으로 변환
            }
            context.startActivity(intent)
        }

        return view
    }

    // 데이터 업데이트 메서드
    fun updateData(newData: List<Restaurant>) {
        data = newData
        notifyDataSetChanged()
    }
}
