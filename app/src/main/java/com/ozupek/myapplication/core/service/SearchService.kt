package com.ozupek.myapplication.core.service

import com.ozupek.myapplication.network.Api
import com.ozupek.myapplication.network.models.RepositoryModel
import io.reactivex.Observable

interface SearchService {

    fun search(keyword: String): Observable<ArrayList<RepositoryModel>>
}

class SearchServiceImpl(private val api: Api): SearchService {

    override fun search(keyword: String): Observable<ArrayList<RepositoryModel>> {
        return api.searchRepositories(keyword)
            .map {
                return@map it.items
            }
    }
}
