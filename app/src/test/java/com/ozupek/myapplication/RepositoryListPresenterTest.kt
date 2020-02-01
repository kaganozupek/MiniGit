package com.ozupek.myapplication

import com.ozupek.myapplication.core.service.SearchService
import com.ozupek.myapplication.network.NetworkManager
import com.ozupek.myapplication.network.models.RepositoryModel
import com.ozupek.myapplication.ui.repositorylist.RepositoryListPresenter
import com.ozupek.myapplication.ui.repositorylist.RepositoryListPresenterImpl
import com.ozupek.myapplication.ui.repositorylist.RepositoryView
import org.junit.Before
import org.junit.Test


class RepositoryListPresenterTest: BaseTest() {


    lateinit var repositoryListPreseter: RepositoryListPresenter

    @Before
    fun setup() {

    }

    @Test
    fun isSearchWorking() {
        repositoryListPreseter = RepositoryListPresenterImpl(SearchService(NetworkManager().api),object : RepositoryView {
            override fun showError() {
            }

            override fun hideProgress() {
            }

            override fun showProgress() {
            }

            override fun showSearchResults(it: ArrayList<RepositoryModel>) {
                assert(it.isNotEmpty())
            }

        })

        repositoryListPreseter.search("Retrofit")
    }
}

