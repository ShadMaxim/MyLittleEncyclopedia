package com.example.mylittleencyclopedia.presentation.details

interface BasePresenterDetails {

    fun setView(view: ViewDetails)
    fun getExampleById(idExample: String, idCategory: String)
    fun detachView()
    fun clickLikes(idCategory: String)
    fun showComments()
    fun sendNewComments(text: String, userName: String)
    fun unShowCloseButtonComments()

    fun sendReport(text: String)
}