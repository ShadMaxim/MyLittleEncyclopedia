package com.example.mylittleencyclopedia.data.provide

import com.example.mylittleencyclopedia.data.model.DataComments
import com.example.mylittleencyclopedia.data.model.DataExampleEncyclopedia
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.PUT
import retrofit2.http.POST

interface EncyclopediaApi {

    @GET("data/beta")
    fun getExampleById(
        @Query("where") charCategory: String
    ): Single<List<DataExampleEncyclopedia>>

    @GET("data/category")
    fun getCategoryByChar(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int,
        @Query("where") char: String
    ): Observable<List<DataExampleEncyclopedia>>

    @GET("data/category/{objectId}")
    fun getCategoryById(
        @Path("objectId") category_id: String
    ): Single<DataExampleEncyclopedia>

    @GET("data/beta")
    fun getExampleBeta(
        @Query("where") charCategory: String
    ): Observable<List<DataExampleEncyclopedia>>

    @PUT("data/category")
    fun updateCountLikes(
        @Body countLikes: DataExampleEncyclopedia
    ): Single<DataExampleEncyclopedia>

    @POST("data/comments")
    fun createCooments(
        @Body comments: DataComments
    ): Single<DataComments>

    @GET("data/comments")
    fun getCommentsByExample(
        @Query("pageSize") pageSize: Int,
        @Query("offset") offset: Int,
        @Query("where") nameExample: String
    ): Observable<List<DataComments>>
}