package com.example.mylittleencyclopedia.presentation.details

import com.example.mylittleencyclopedia.data.model.DataComments
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia

interface ViewDetails {

    fun showExample(example: DataExampleEncyclopedia)
    fun showToastOk(text: String)
    fun showToastError(text: String)
    fun unShowLikes(text: String)
    fun unShowSendComment()
    fun unShowCloseButtonComment()
    fun showCloseComment()
    fun unShowButtonShowComment()
    fun showComments(listComments: List<DataComments>)
}