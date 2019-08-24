package com.example.mylittleencyclopedia.presentation.details

interface BasePresenterDetails {

    fun setView(view: ViewDetails)
    fun getExampleById(idExample: String)
    fun detachView()
}