package com.example.mylittleencyclopedia.presentation.details

import com.example.mylittleencyclopedia.data.model.DataComments
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.data.provide.provideEncyclopediaRepository
import com.yandex.metrica.YandexMetrica
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Integer.parseInt
import java.text.SimpleDateFormat
import java.util.Date

class PresenterDetails : BasePresenterDetails {

    private var view: ViewDetails? = null
    var disposable: Disposable? = null
    private val repository = provideEncyclopediaRepository()
    var example: DataExampleEncyclopedia? = null
    var exampleCategory: DataExampleEncyclopedia? = null

    var listComments: MutableList<DataComments> = mutableListOf()
    var listData: MutableList<String> = mutableListOf()

    override fun setView(view: ViewDetails) {
        this.view = view
    }

    override fun getExampleById(idExample: String, idCategory: String) {

        disposable = repository
            .getExampleById(idExample)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                example = it[0]
                view?.showExample(example!!)
            }, {

                view?.showToastError("""Error : $it""")
            })

        disposable = repository
            .getCategoryById(idCategory)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                exampleCategory = it
            }, {

                view?.showToastError("""Error : $it""")
            })
    }

    override fun clickLikes(idCategory: String) {

        val idObject: String = exampleCategory!!.idObject
        val category: String = exampleCategory!!.category
        val idCategry: String = exampleCategory!!.id_category
        val categoryImage: String = exampleCategory!!.category_image
        val countLikes: String = exampleCategory!!.count_likes
        val created: String = exampleCategory!!.created

        val temp = parseInt(countLikes)
        val countClickAdd = (temp + 1).toString()
        val updateCategory = DataExampleEncyclopedia(idObject, category, idCategry, categoryImage, countClickAdd, created)

        disposable = repository
            .updateCountLikes(updateCategory)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                view?.unShowLikes(example!!.name)
            }, {

                view?.showToastError("""Error : $it""")
            })
    }

    override fun showComments() {

        disposable = repository
            .getCommentsByExample(100, 0, example!!.name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                listComments.clear()
                listComments.addAll(it)

                listComments.sortWith(compareBy({ it.dataCreateComments }))
                listComments.reverse()

                listData.addAll(getTimeStamp(listComments))
                listData.sort()
                listData.reverse()

                view?.showComments(listComments, listData)
                view?.unShowButtonShowComment()
                view?.showCloseComment()
            }, {

                view?.showToastError("""Error : $it""")
            })
    }

    override fun sendNewComments(text: String, userName: String) {

        val comments = DataComments(example!!.name, text, userName)

        disposable = repository
            .createComments(comments)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                view?.unShowSendComment()
            }, {

                view?.showToastError("""Error : $it""")
            })
    }

    override fun unShowCloseButtonComments() {
        view?.unShowCloseButtonComment()
    }

    private fun getTimeStamp(list: MutableList<DataComments>): List<String> {

        val newListData: MutableList<String> = mutableListOf()

        for (i in 0 until list.size) {

            val date: String?
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            date = formatter.format(Date(list[i].dataCreateComments.toLong()))

            newListData.add(date)
        }
        return newListData
    }

    override fun detachView() {
        this.view = null
        disposable!!.dispose()
    }

    override fun sendReport(text: String) {
        val clickLikes = "{\"title\":\"$text\"}"
        YandexMetrica.reportEvent("ClickLikes", clickLikes)
    }
}