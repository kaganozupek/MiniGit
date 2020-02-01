package com.ozupek.myapplication.ui.repositorylist

import com.ozupek.myapplication.network.models.RepositoryModel

interface RepositoryView {

    fun showError()
    fun hideProgress()
    fun showProgress()
    fun showSearchResults(it: ArrayList<RepositoryModel>)
}

interface RepositoryListPresenter {
    fun search(keyword: String)
}