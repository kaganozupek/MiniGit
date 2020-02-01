package com.ozupek.myapplication.ui.repositorylist

import com.ozupek.myapplication.core.service.SearchService
import com.ozupek.myapplication.network.models.RepositoryModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class RepositoryListPresenterImpl(
    private val searchService: SearchService,
    private val view: RepositoryView
) : RepositoryListPresenter {
    private var pageNumber = 1
    private var lastKeyword : String? = null
    private var searchDisposable: Disposable? = null
    private var loadMoreDisposable : Disposable? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onTextChanged(keyword: String) {
        searchDisposable?.dispose()
        if (keyword.length < 2) {
            return
        }
        pageNumber = 1
        lastKeyword = keyword
        view.showProgress()
        searchDisposable = searchService.search(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showResult(it)
                view.hideProgress()
            }, {
                it.printStackTrace()
                view.showError(it.localizedMessage)

            })
    }

    override fun loadMore() {
        loadMoreDisposable?.dispose()
        pageNumber += 1
        lastKeyword?.let {
            searchService.search(it,pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showMoreResult(it)
                },{
                    view.showError(it.localizedMessage)
                })
        }
    }

    override fun onItemCliced(repositoryModel: RepositoryModel) {
        view.goToUserDetail(repositoryModel)
    }

}