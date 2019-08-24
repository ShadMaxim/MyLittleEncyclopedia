package com.example.mylittleencyclopedia.data.provide

import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import io.reactivex.Observable
import io.reactivex.Single

class EncyclopediaRepositoryRemote(private var api: EncyclopediaApi) : EncyclopediaRepository {

    override fun get(pageSize: Int, offset: Int): Observable<List<DataExampleEncyclopedia>> {
        return api.getExample(pageSize, offset)
    }

    override fun getExampleList(charCategory: String): Observable<List<DataExampleEncyclopedia>> {
        return api.getExampleList("category = '$charCategory'")
    }

    override fun getExampleById(charCategory: String): Single<List<DataExampleEncyclopedia>> {
        return api.getExampleById("objectId = '$charCategory'")
    }

    override fun search(pageSize: Int, offset: Int, charName: String): Observable<List<DataExampleEncyclopedia>> {
        return api.searchByCharOrName(pageSize, offset, "name LIKE '%$charName%'")
    }

    override fun getCategoryByUserId(pageSize: Int, offset: Int, category_id: String): Observable<List<DataExampleEncyclopedia>> {
        return api.getCategoryByUserId(pageSize, offset, "category LIKE '%$category_id%'")
    }

    override fun getExampleBeta(charCategory: String): Observable<List<DataExampleEncyclopedia>> {
        return api.getExampleBeta("id_category = '$charCategory'")
    }

    override fun getExampleBetaSearch(charCategory: String): Observable<List<DataExampleEncyclopedia>> {
        return api.getExampleBeta("d_object = '$charCategory'")
    }

    override fun getAll(pageSize: Int, offset: Int): Observable<List<DataExampleEncyclopedia>> {
        return api.getAll(pageSize, offset)
    }
}