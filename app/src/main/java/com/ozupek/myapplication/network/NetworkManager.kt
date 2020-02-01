package com.ozupek.myapplication.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager private constructor() {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private var api: Api

    init {
        api = retrofit.create(Api::class.java)
    }

    companion object {
        private val networkManager = NetworkManager()

        fun getApi(): Api {
            return networkManager.api
        }
    }

}