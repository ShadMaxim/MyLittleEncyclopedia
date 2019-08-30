package com.example.mylittleencyclopedia.adapter.adapterCategory

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.util.picassoLoader

class CategoryListViewHolder(view: View) : RecyclerView.ViewHolder(view), ViewHolder {

    private val imageViewHead = itemView.findViewById<ImageView>(R.id.imageViewCategoryHead)
    private val nameCategoryTextView = itemView.findViewById<TextView>(R.id.nameCategoryTextView)
    private val textViewDate = itemView.findViewById<TextView>(R.id.textViewDate)
    private val countLikes = itemView.findViewById<TextView>(R.id.count_likes)

    private var presenter: PresenterHolder? = null

    fun bind(exampleEncyclopedia: DataExampleEncyclopedia) {

        presenter = PresenterHolder()
        presenter!!.setView(this)

        nameCategoryTextView.text = exampleEncyclopedia.category
        countLikes.text = exampleEncyclopedia.count_likes
        picassoLoader(exampleEncyclopedia.category_image, imageViewHead)

        presenter!!.getTimeStamp(exampleEncyclopedia.created.toLong())
    }

    override fun showDate(date: String) {
        textViewDate.text = date
    }
}