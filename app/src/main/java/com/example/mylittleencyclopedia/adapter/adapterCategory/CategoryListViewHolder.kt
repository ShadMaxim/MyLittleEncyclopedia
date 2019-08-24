package com.example.mylittleencyclopedia.adapter.adapterCategory

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.util.picassoLoader

class CategoryListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imageViewHead = itemView.findViewById<ImageView>(R.id.imageViewCategoryHead)
    private val nameCategoryTextView = itemView.findViewById<TextView>(R.id.nameCategoryTextView)
    private val textViewDate = itemView.findViewById<TextView>(R.id.textViewDate)
    private val countLikes = itemView.findViewById<TextView>(R.id.count_likes)

    fun bind(exampleEncyclopedia: DataExampleEncyclopedia) {

        /*if (exampleEncyclopedia.zzz != null){
            Log.e("AAA", " zzz = not null")
        }else {
            Log.e("AAA", " zzz =  null")}*/

        nameCategoryTextView.text = exampleEncyclopedia.category
        textViewDate.text = exampleEncyclopedia.date_up
        countLikes.text = exampleEncyclopedia.count_likes
        picassoLoader(exampleEncyclopedia.category_image, imageViewHead)
    }
}