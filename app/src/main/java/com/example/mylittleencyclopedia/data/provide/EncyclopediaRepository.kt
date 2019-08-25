package com.example.mylittleencyclopedia.data.provide

import com.example.mylittleencyclopedia.data.model.DataComments
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import io.reactivex.Observable
import io.reactivex.Single

interface EncyclopediaRepository {

    fun get(pageSize: Int, offset: Int): Observable<List<DataExampleEncyclopedia>>
    fun getExampleById(charCategory: String): Single<List<DataExampleEncyclopedia>>
    fun getExampleList(charCategory: String): Observable<List<DataExampleEncyclopedia>>

    fun search(pageSize: Int, offset: Int, charName: String): Observable<List<DataExampleEncyclopedia>>

    fun getCategoryByChar(pageSize: Int, offset: Int, char: String): Observable<List<DataExampleEncyclopedia>>
    fun getCategoryById(category_id: String): Single<DataExampleEncyclopedia>

    fun getExampleBeta(charCategory: String): Observable<List<DataExampleEncyclopedia>>
    fun getExampleBetaSearch(charCategory: String): Observable<List<DataExampleEncyclopedia>>
    fun getAll(pageSize: Int, offset: Int): Observable<List<DataExampleEncyclopedia>>

    fun updateCountLikes(example: DataExampleEncyclopedia): Single<DataExampleEncyclopedia>

    fun getCommentsByExample(pageSize: Int, offset: Int, nameExample: String): Observable<List<DataComments>>
    fun createComments(comments: DataComments): Single<DataComments>
}