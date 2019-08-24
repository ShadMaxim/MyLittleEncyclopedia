package com.example.mylittleencyclopedia.presentation.details

import android.util.Log
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.data.provide. provideEncyclopediaRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PresenterDetails : BasePresenterDetails {

    private var view: ViewDetails? = null
    var disposable: Disposable? = null
    private val repository = provideEncyclopediaRepository()
    var example: DataExampleEncyclopedia? = null

    override fun setView(view: ViewDetails) {
        this.view = view
    }

    override fun getExampleById(idExample: String) {

        disposable = repository
            .getExampleById(idExample)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                Log.e("AAA it Details =  ", it.toString())
                Log.e("AAA it size =  ", it.size.toString())

                example = it[0]
                Log.e("AAA example =  ", example.toString())

                view?.showExample(example!!)
            }, {

                view?.showToastError("""Error : $it""")
                Log.e("AAA Error =  ", it.toString())
            })
    }

    override fun detachView() {
        this.view = null
        disposable!!.dispose()
    }
}