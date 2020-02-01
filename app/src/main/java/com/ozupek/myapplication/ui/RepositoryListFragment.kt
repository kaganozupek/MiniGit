package com.ozupek.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozupek.myapplication.R
import com.ozupek.myapplication.network.NetworkManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_repository_list.*

class RepositoryListFragment : BaseFragment(R.layout.fragment_repository_list) {

    private var adapter: RepositoryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchRepository(keyword = "Retrofit")
    }

    override fun initViews() {
        rclView.adapter = RepositoryAdapter().apply {
            this@RepositoryListFragment.adapter = this
        }
        rclView.layoutManager = LinearLayoutManager(fragmentContext)
        adapter?.notifyDataSetChanged()
    }

    private fun searchRepository(keyword: String) {
        showProgress()
        NetworkManager.getApi().searchRepositories(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                hideProgress()
                adapter?.bindData(it.items)
            }, {
                hideProgress()
                showError()
            }).addTo(compositeDisposable)
    }

    private fun showError() {
        Toast.makeText(fragmentContext,"An error occured while fetching data",Toast.LENGTH_LONG).show()
    }

    private fun hideProgress() {
        prgs.visibility = View.GONE
    }

    private fun showProgress() {
        prgs.visibility = View.VISIBLE
    }
}

