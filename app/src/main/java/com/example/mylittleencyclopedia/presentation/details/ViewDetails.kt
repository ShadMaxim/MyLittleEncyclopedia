package com.example.mylittleencyclopedia.presentation.details

import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia

interface ViewDetails {

    fun showExample(example: DataExampleEncyclopedia)
    fun showToastOk(text: String)
    fun showToastError(text: String)
    fun unShowLikes()
}