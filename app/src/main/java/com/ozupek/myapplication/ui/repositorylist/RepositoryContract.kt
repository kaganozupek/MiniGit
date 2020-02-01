package com.ozupek.myapplication.ui.repositorylist

import com.ozupek.myapplication.network.models.RepositoryModel

interface RepositoryView {
    fun showResult(results: ArrayList<RepositoryModel>?)
    fun showProgress()
    fun hideProgress()
    fun showError(message: String)
    fun showMoreResult(result: ArrayList<RepositoryModel>)
    fun goToUserDetail(repositoryModel: RepositoryModel)
}
interface RepositoryListPresenter {
    fun onTextChanged(toString: String)
    fun loadMore()
    fun onItemCliced(repositoryModel: RepositoryModel)
}

