package com.example.mylittleencyclopedia.data.provide

import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import io.reactivex.Observable
import io.reactivex.Single

interface EncyclopediaRepository {

    fun get(pageSize: Int, offset: Int): Observable<List<DataExampleEncyclopedia>>
    fun getExampleById(charCategory: String): Single<List<DataExampleEncyclopedia>>
    fun getExampleList(charCategory: String): Observable<List<DataExampleEncyclopedia>>

    fun search(pageSize: Int, offset: Int, charName: String): Observable<List<DataExampleEncyclopedia>>
    fun getCategoryByUserId(pageSize: Int, offset: Int, category_id: String): Observable<List<DataExampleEncyclopedia>>

    fun getExampleBeta(charCategory: String): Observable<List<DataExampleEncyclopedia>>
    fun getExampleBetaSearch(charCategory: String): Observable<List<DataExampleEncyclopedia>>
    fun getAll(pageSize: Int, offset: Int): Observable<List<DataExampleEncyclopedia>>
}