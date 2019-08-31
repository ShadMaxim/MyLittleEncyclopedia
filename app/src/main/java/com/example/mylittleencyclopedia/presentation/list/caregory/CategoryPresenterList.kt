package com.example.mylittleencyclopedia.presentation.list.caregory

import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.data.provide.provideEncyclopediaRepository
import com.yandex.metrica.YandexMetrica
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CategoryPresenterList : CategoryBasePresenterList {

    private var view: CategoryViewList? = null
    var listOfCategory: MutableList<DataExampleEncyclopedia> = mutableListOf()
    private val repository = provideEncyclopediaRepository()
    var disposable: Disposable? = null
    private val numberPage = 10

    override fun setView(view: CategoryViewList) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        disposable?.dispose()
    }

    override fun newListForSearch(text: String): MutableList<DataExampleEncyclopedia> {
        val list = listOfCategory
        view?.showNewList(list)
        return list
    }

    override fun loadList(text: String) {

        disposable = repository
            .getCategoryByChar(numberPage, 0, text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->

                listOfCategory.clear()
                listOfCategory.addAll(data)
                val list = listOfCategory

                view?.showNewList(list)
            }, { throwable ->

                view?.showError(throwable.toString())
            })
    }

    override fun reloadRecycler() {
        val list = listOfCategory
        view?.showNewList(list)
    }

    fun loadMore(page: Int, text: String) {

        disposable = repository
            .getCategoryByChar(numberPage, numberPage*page, text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->

                listOfCategory.addAll(data)
                val list = listOfCategory

                view?.showNewList(list)
            }, { throwable ->

                view?.showError(throwable.toString())
            })
    }

    fun sendReport(text: String) {

        val paramsCategory = "{\"title\":\"$text\"}"
        YandexMetrica.reportEvent("SelectedCategory", paramsCategory)
    }
}
