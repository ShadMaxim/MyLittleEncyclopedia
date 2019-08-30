package com.example.mylittleencyclopedia.presentation.start

interface StartBasePresenterList {

    fun setView(view: StartViewList)
    fun detachView()
    fun sendReport(text: String)
}