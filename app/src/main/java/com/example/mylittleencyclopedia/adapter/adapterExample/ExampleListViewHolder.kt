package com.example.mylittleencyclopedia.adapter.adapterExample

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.util.picassoLoader

class ExampleListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imageViewHead = itemView.findViewById<ImageView>(R.id.imageViewExampleHead)
    private val imageViewOne = itemView.findViewById<ImageView>(R.id.imageViewExampleOne)
    private val imageViewTwo = itemView.findViewById<ImageView>(R.id.imageViewExampleTwo)

    private val nameExampleTextView = itemView.findViewById<TextView>(R.id.nameExampleTextView)

    fun bind(exampleEncyclopedia: DataExampleEncyclopedia) {

        picassoLoader(exampleEncyclopedia.url_box_one, imageViewHead)
        picassoLoader(exampleEncyclopedia.url_box_four, imageViewOne)
        picassoLoader(exampleEncyclopedia.url_box_seven, imageViewTwo)

        nameExampleTextView.text = exampleEncyclopedia.name
    }
}