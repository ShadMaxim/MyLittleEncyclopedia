package com.example.mylittleencyclopedia.adapter.adapterExample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mylittleencyclopedia.R
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia

class ExampleListAdapter
    (private var items: MutableList<DataExampleEncyclopedia>, private val listener: ClickListener)
    : RecyclerView.Adapter<ExampleListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleListViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_example, parent, false)

        val holder = ExampleListViewHolder(view)
        holder.itemView.setOnClickListener {
            listener.onExampleClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: ExampleListViewHolder, position: Int) {
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
        fun onExampleClick(item: DataExampleEncyclopedia)
    }
}