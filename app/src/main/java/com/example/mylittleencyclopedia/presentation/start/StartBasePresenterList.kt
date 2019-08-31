package com.example.mylittleencyclopedia.presentation.start

interface StartBasePresenterList {

    fun setView(view: StartView)
    fun detachView()
    fun sendReport(text: String)
}