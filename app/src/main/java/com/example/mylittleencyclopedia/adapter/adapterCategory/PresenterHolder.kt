package com.example.mylittleencyclopedia.adapter.adapterCategory

import java.text.SimpleDateFormat
import java.util.Date

class PresenterHolder {

    private var view: ViewHolder? = null

    fun setView(view: ViewHolder) {
        this.view = view
    }

    fun getTimeStamp(timeInMillies: Long): String {

        var date: String? = null
        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        date = formatter.format(Date(timeInMillies))
        view?.showDate(date)
        return date
    }

    fun detachView() {
        this.view = null
    }
}