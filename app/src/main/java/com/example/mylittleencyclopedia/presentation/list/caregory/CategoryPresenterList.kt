package com.example.mylittleencyclopedia.presentation.list.caregory

import android.util.Log
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.data.provide.provideEncyclopediaRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CategoryPresenterList : CategoryBasePresenterList {

    private var view: CategoryViewList? = null
    var listOfCategory: MutableList<DataExampleEncyclopedia> = mutableListOf()
    private val repository = provideEncyclopediaRepository()
    var disposable: Disposable? = null
    // var charInFilter = ""
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

        // view?.showProgressBar()
        // charInFilter = text

        disposable = repository
            .getCategoryByChar(numberPage, 0, text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->

                listOfCategory.clear()

                listOfCategory.addAll(data)
                val list = listOfCategory

                Log.e("AAA List  ", data.toString())
                Log.e("AAA List size ", data.size.toString())

                Log.e("AA listOfCategory size ", listOfCategory.size.toString())

                view?.showNewList(list)
                // view?.notShowProgressBar()
            }, { throwable ->

                Log.e("AAA Error", throwable.toString())
            })
    }

    override fun reloadRecycler() {
        val list = listOfCategory
        view?.showNewList(list)
    }

    fun loadMore(page: Int, text: String) {

        disposable = repository
            .search(numberPage, numberPage*page, text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->

                listOfCategory.addAll(data)
                val list = listOfCategory

                view?.showNewList(list)
                // view?.showToastGetOk(" list load successfully ")
            }, { throwable ->

                // view?.showToastGetError(throwable.toString())
                Log.e("AAA Error", throwable.toString())
            })
    }

    fun firstLoadListCategory() {

        view?.showProgressBar()

        disposable = repository
            .getCategoryByChar(numberPage, 0, "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->

                listOfCategory.clear()

                listOfCategory.addAll(data)
                val list = listOfCategory

                Log.e("AAA List  ", data.toString())
                Log.e("AAA List size ", data.size.toString())

                Log.e("AA listOfCategory size ", listOfCategory.size.toString())

                view?.showNewList(list)
                view?.notShowProgressBar()
            }, { throwable ->

                Log.e("AAA Error", throwable.toString())
            })
    }
}
