package com.ozupek.myapplication.core.service

import com.ozupek.myapplication.network.Api

interface SearchService {

}

class SearchServiceImpl(private val api: Api): SearchService {

    fun search()

}



