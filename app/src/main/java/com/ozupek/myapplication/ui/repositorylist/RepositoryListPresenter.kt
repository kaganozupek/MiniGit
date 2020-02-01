package com.ozupek.myapplication.ui.repositorylist

import com.ozupek.myapplication.core.service.SearchService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers


class RepositoryListPresenterImpl(
    private val searchService: SearchService,
    private val view: RepositoryView
) : RepositoryListPresenter {

    private val compositeDisposable: CompositeDisposable =
        CompositeDisposable()


    override fun search(keyword: String) {

        view.showProgress()
        searchService.search(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.showSearchResults(it)
            }, {
                view.hideProgress()
                view.showError()
            }).addTo(compositeDisposable)
    }

}