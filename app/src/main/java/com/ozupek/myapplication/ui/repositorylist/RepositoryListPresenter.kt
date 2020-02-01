package com.ozupek.myapplication.ui.repositorylist

import com.ozupek.myapplication.core.service.SearchService
import io.reactivex.disposables.CompositeDisposable


class RepositoryListPresenterImpl(
    private val searchService: SearchService,
    private val view: RepositoryView
) : RepositoryListPresenter {
    override fun onTextChanged(toString: String) {

    }

    private val compositeDisposable: CompositeDisposable =
        CompositeDisposable()


}