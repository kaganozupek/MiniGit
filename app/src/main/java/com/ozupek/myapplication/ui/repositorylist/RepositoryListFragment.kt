package com.ozupek.myapplication.ui.repositorylist

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozupek.myapplication.R
import com.ozupek.myapplication.core.service.SearchServiceImpl
import com.ozupek.myapplication.core.ui.BaseFragment
import com.ozupek.myapplication.network.NetworkManager
import kotlinx.android.synthetic.main.fragment_repository_list.*


class RepositoryListFragment : BaseFragment(R.layout.fragment_repository_list),
    RepositoryView {

    private var adapter: RepositoryAdapter? = null
    private lateinit var presenter: RepositoryListPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = RepositoryListPresenterImpl(
            SearchServiceImpl(
                NetworkManager().api
            ), this
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initViews() {
        rclView.adapter = RepositoryAdapter().apply {
            this@RepositoryListFragment.adapter = this
        }
        rclView.layoutManager = LinearLayoutManager(fragmentContext)
        adapter?.notifyDataSetChanged()

        etSearch.addTextChangedListener {

            presenter.onTextChanged(it.toString())


        }

    }


}


