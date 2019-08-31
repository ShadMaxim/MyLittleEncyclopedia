package com.example.mylittleencyclopedia.data.provide

import com.example.mylittleencyclopedia.data.model.DataComments
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import io.reactivex.Observable
import io.reactivex.Single

class EncyclopediaRepositoryRemote(private var api: EncyclopediaApi) : EncyclopediaRepository {

    override fun getExampleById(charCategory: String): Single<List<DataExampleEncyclopedia>> {
        return api.getExampleById("objectId = '$charCategory'")
    }

    override fun getCategoryByChar(pageSize: Int, offset: Int, char: String): Observable<List<DataExampleEncyclopedia>> {
        return api.getCategoryByChar(pageSize, offset, "category LIKE '%$char%'")
    }

    override fun getCategoryById(category_id: String): Single<DataExampleEncyclopedia> {
        return api.getCategoryById(category_id)
    }

    override fun getExampleBeta(charCategory: String): Observable<List<DataExampleEncyclopedia>> {
        return api.getExampleBeta("id_category = '$charCategory'")
    }

    override fun updateCountLikes(example: DataExampleEncyclopedia): Single<DataExampleEncyclopedia> {
        return api.updateCountLikes(example)
    }

    override fun getCommentsByExample(pageSize: Int, offset: Int, nameExample: String): Observable<List<DataComments>> {
        return api.getCommentsByExample(pageSize, offset, "name_object LIKE '%$nameExample%'")
    }

    override fun createComments(comments: DataComments): Single<DataComments> {
        return api.createCooments(comments)
    }
}