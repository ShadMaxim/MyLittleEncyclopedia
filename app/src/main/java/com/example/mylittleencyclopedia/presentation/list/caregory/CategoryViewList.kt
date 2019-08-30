package com.example.mylittleencyclopedia.presentation.list.caregory

import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia

interface CategoryViewList {

    fun showNewList(list: MutableList<DataExampleEncyclopedia>)
    fun showProgressBar()
    fun notShowProgressBar()
    fun showError(text: String)
}