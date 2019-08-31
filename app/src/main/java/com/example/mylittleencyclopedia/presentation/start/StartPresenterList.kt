package com.example.mylittleencyclopedia.presentation.start

import com.yandex.metrica.YandexMetrica

class StartPresenterList : StartBasePresenterList {

    private var view: StartView? = null

    override fun setView(view: StartView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun sendReport(text: String) {
        val paramsUserName = "{\"name\":\"$text\"}"
        YandexMetrica.reportEvent("NewUser", paramsUserName)

        view?.showToastHello(text)
    }
}