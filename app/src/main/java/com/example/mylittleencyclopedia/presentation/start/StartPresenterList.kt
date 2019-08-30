package com.example.mylittleencyclopedia.presentation.start

import com.yandex.metrica.YandexMetrica

class StartPresenterList : StartBasePresenterList {

    private var view: StartViewList? = null

    override fun setView(view: StartViewList) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun sendReport(text: String) {
        val paramsUserName: String = "{\"name\":\"$text\"}"
        YandexMetrica.reportEvent("NewUser", paramsUserName)
    }
}