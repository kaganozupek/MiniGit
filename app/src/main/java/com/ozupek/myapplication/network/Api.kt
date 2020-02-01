package com.ozupek.myapplication.network

import com.ozupek.myapplication.network.models.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    //https://api.github.com/search/repositories?q=retrofit&per_page=100&page=1

    @GET("/search/repositories")
    fun searchRepositories(@Query("q") keyword: String,
                           @Query("per_page") perPage: Int = 100,
                           @Query("page") page: Int = 1
    ): Observable<SearchResponse>

}