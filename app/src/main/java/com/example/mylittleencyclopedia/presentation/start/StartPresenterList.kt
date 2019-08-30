package com.example.mylittleencyclopedia.presentation.start

class StartPresenterList : StartBasePresenterList {

    private var view: StartViewList? = null

    override fun setView(view: StartViewList) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}