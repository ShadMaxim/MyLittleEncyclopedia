package com.example.mylittleencyclopedia.presentation.list.example

import android.util.Log
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.data.provide.provideEncyclopediaRepository
import com.yandex.metrica.YandexMetrica
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ExamplePresenterList : ExampleBasePresenterList {

    private var view: ExampleViewList? = null
    var listOfExample: MutableList<DataExampleEncyclopedia> = mutableListOf()
    private val repository = provideEncyclopediaRepository()
    var disposable: Disposable? = null

    override fun setView(view: ExampleViewList) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable?.dispose()
    }

    override fun newListForSearch(text: String): MutableList<DataExampleEncyclopedia> {
        val list = listOfExample
        view?.showNewList(list)
        return list
    }

    override fun firstLoadListExample(text: String, id_category: String) {

        view?.showProgressBar()

        disposable = repository
            .getExampleBeta(id_category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->

                listOfExample.clear()
                listOfExample.addAll(data)
                val list = listOfExample

                view?.showNewList(list)
                view?.notShowProgressBar()
            }, { throwable ->

                Log.e("AAA Error", throwable.toString())
                view?.showError(throwable.toString())
            })
    }

    override fun reloadRecycler() {
        val list = listOfExample
        view?.showNewList(list)
    }

    fun sendReport(text: String) {
        val paramsExample = "{\"example\":\"$text\"}"
        YandexMetrica.reportEvent("SelectedExample", paramsExample)
    }
}
