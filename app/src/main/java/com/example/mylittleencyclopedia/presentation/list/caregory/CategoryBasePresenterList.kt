package com.example.mylittleencyclopedia.presentation.list.caregory

import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia

interface CategoryBasePresenterList {

    fun setView(view: CategoryViewList)
    fun detachView()
    fun newListForSearch(text: String): MutableList<DataExampleEncyclopedia>
    fun loadList(text: String)
    fun reloadRecycler()
}