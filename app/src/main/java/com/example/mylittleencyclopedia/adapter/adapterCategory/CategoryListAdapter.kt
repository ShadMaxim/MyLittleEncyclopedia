package com.example.mylittleencyclopedia.adapter.adapterCategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia

class CategoryListAdapter
    (private var items: MutableList<DataExampleEncyclopedia>, private val listener: ClickListener)
    : RecyclerView.Adapter<CategoryListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {

        // val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_category_ver_one, parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_item, parent, false)

        val holder = CategoryListViewHolder(view)
        holder.itemView.setOnClickListener {
            listener.onCategoryClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(list: MutableList<DataExampleEncyclopedia>) {
        items = list
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onCategoryClick(item: DataExampleEncyclopedia)
    }
}