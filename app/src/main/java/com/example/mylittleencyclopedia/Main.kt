/*
package com.example.mylittleencyclopedia

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import com.example.mylittleencyclopedia.data.provide.provideEncyclopediaRepository
// import com.example.mylittleencyclopedia.data.provide.provideEncyclopediaRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Main : Activity() {

    private var disposable: Disposable? = null
    private val repository = provideEncyclopediaRepository()
    var listEncyclop : MutableList<String> = mutableListOf()
    var listCategory : HashSet<String> = hashSetOf()
    var text = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_fragments)

        disposable = repository
            .getAll( 10, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->

                Log.e("AAA All ", data.toString())

                for (i in 0 until data.size){
                    listEncyclop.add(data[i].name)
                    listCategory.add(data[i].category)
                }

                Log.e("AAA List name ", listEncyclop.toString())
                Log.e("AAA List category  ", listCategory.toString())


                */
/*Log.e("AAA url 0 ", data[0].url_box_one)
                Log.e("AAA url 1 ", data[1].url_box_one)
                Log.e("AAA url 2 ", data[2].url_box_one)

                Log.e("AAA", listCategory.toString())
                Log.e("AAA", listCategory.size.toString())
                Log.e("AAA", data.toString())
                Log.e("AAA", data[0].category.toString())
                Log.e("AAA", data[0].name.toString())
                Log.e("AAA", data[0].text_box_eight.toString())
                Log.e("AAA", data[0].text_box_five.toString())*//*


            }, { throwable ->
                throwable.message
                Log.e("AAA Error = ", throwable.toString())
            })
    }
}*/
