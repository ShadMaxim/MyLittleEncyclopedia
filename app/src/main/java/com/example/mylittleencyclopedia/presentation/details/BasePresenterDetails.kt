package com.example.mylittleencyclopedia.presentation.details

interface BasePresenterDetails {

    fun setView(view: ViewDetails)
    fun getExampleById(idExample: String, idObjectCategory: String)
    fun detachView()
    fun clickLikes(idObjectCategory: String)
    fun showComments()
    fun sendNewComments(text: String, userName: String)
    fun unShowCloseButtonComments()

    fun sendReport(text: String)
}