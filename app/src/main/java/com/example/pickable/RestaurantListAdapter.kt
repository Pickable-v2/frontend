package com.example.pickable

import android.content.Context
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
            // 버튼 클릭 시 동작 정의 (필요에 따라 추가 구현)
        }

        return view
    }

    // 데이터 업데이트 메서드
    fun updateData(newData: List<Restaurant>) {
        data = newData
        notifyDataSetChanged()
    }
}
