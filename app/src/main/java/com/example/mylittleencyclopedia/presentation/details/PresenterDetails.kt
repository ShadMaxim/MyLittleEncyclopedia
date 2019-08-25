package com.example.mylittleencyclopedia.presentation.details

import android.util.Log
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.data.provide.provideEncyclopediaRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Integer.parseInt
import java.text.SimpleDateFormat
import java.util.*

class PresenterDetails : BasePresenterDetails {

    private var view: ViewDetails? = null
    var disposable: Disposable? = null
    private val repository = provideEncyclopediaRepository()
    var example: DataExampleEncyclopedia? = null
    var exampleCategory: DataExampleEncyclopedia? = null

    override fun setView(view: ViewDetails) {
        this.view = view
    }

    override fun getExampleById(idExample: String, idCategory: String) {

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

        disposable = repository
            .getCategoryById(idCategory)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                exampleCategory = it
                Log.e("AAA exampleCategory Rx=", exampleCategory.toString())

                //view?.showExample("")
                //view?.unShowLikes()
            }, {

                view?.showToastError("""Error : $it""")
                Log.e("AAA Error =  ", it.toString())
            })
    }

    override fun clickLikes(idCategory: String) {

        val idObject: String = exampleCategory!!.idObject
        val category: String = exampleCategory!!.category
        val idcategory: String = exampleCategory!!.id_category
        val categoryImage: String = exampleCategory!!.category_image
        val countLikes: String = exampleCategory!!.count_likes
        val dateUp: String = exampleCategory!!.date_up
        val updated: String = exampleCategory!!.updated


        val temp = parseInt(countLikes)
        val tempUpdate: Long = updated.toLong()
        val x = (temp + 1).toString()
        Log.e("AAA exampleCategory =  ", exampleCategory.toString())
        Log.e("AAA temp =  ", temp.toString())
        Log.e("AAA x =  ", x.toString())
        Log.e("AAA tempUpdate =  ", tempUpdate.toString())

        getTimeStamp(tempUpdate)
        Log.e("AAA date =  ", getTimeStamp(tempUpdate))

        val updateCategory = DataExampleEncyclopedia(idObject, category, idcategory, categoryImage, x, dateUp, updated)

        disposable = repository
            .updateCountLikes(updateCategory)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                Log.e("AAA it update = ", it.toString())


                view?.unShowLikes()
            }, {

                view?.showToastError("""Error : $it""")
                Log.e("AAA Error =  ", it.toString())
            })
    }

    override fun detachView() {
        this.view = null
        disposable!!.dispose()
    }

    fun getTimeStamp(timeInMillies: Long): String {
        var date: String? = null
        val formatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd") // modify format
        date = formatter.format(Date(timeInMillies))
        System.out.println("Today is " + date)

        return date
    }
}