package com.example.mylittleencyclopedia.presentation.list.caregory

import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia

interface CategoryViewList {

    fun showNewList(list: MutableList<DataExampleEncyclopedia>)
    /*fun showToastGetOk(text: String)
    fun showToastGetError(text: String)
    fun showTimerError(text: String)*/

    fun showProgressBar()
    fun notShowProgressBar()
}