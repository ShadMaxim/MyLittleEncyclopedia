package com.example.mylittleencyclopedia.presentation.list.example

import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia

interface ExampleBasePresenterList {

    fun setView(view: ExampleViewList)
    fun detachView()
    fun newListForSearch(text: String): MutableList<DataExampleEncyclopedia>
    fun firstLoadListExample(text: String, id_category: String)
    fun reloadRecycler()
}