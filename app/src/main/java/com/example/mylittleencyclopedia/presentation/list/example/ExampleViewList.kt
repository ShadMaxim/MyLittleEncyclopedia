package com.example.mylittleencyclopedia.presentation.list.example

import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia

interface ExampleViewList {

    fun showNewList(list: MutableList<DataExampleEncyclopedia>)
    fun showError(text: String)
    fun showProgressBar()
    fun notShowProgressBar()
}