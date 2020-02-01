package com.ozupek.myapplication.ui.repositorylist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ozupek.myapplication.R
import com.ozupek.myapplication.core.service.SearchServiceImpl
import com.ozupek.myapplication.core.ui.BaseFragment
import com.ozupek.myapplication.network.NetworkManager
import com.ozupek.myapplication.network.models.RepositoryModel
import com.ozupek.myapplication.ui.userdetail.KEY_REPOSITORY_MODEL
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
        rclView.adapter = RepositoryAdapter(onItemClickListener = {
            presenter.onItemCliced(it)
        }).apply {
            this@RepositoryListFragment.adapter = this
        }
        rclView.layoutManager = LinearLayoutManager(fragmentContext)
        adapter?.notifyDataSetChanged()
        etSearch.addTextChangedListener {
            presenter.onTextChanged(it.toString())
        }

        rclView.addOnScrollListener(OnRecyclerViewAtBottomListener {
           presenter.loadMore()

        })
    }

    override fun showResult(results: ArrayList<RepositoryModel>?) {
        results?.let {
            adapter?.clearData()
            adapter?.bindData(it)
        }
    }

    override fun showProgress() {
        rclView.visibility = View.GONE
        prgs.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        prgs.visibility = View.GONE
        rclView.visibility = View.VISIBLE
    }

    override fun showError(message: String) {
        Toast.makeText(fragmentContext, message, Toast.LENGTH_LONG).show()
    }

    override fun showMoreResult(result: ArrayList<RepositoryModel>) {
        adapter?.bindData(result)
    }

    override fun goToUserDetail(repositoryModel: RepositoryModel) {
        findNavController().navigate(R.id.action_repositoryListFragment_to_userDetailFragment,Bundle().apply {
            putParcelable(KEY_REPOSITORY_MODEL,repositoryModel.owner)
        })
    }
}

class OnRecyclerViewAtBottomListener(val onBottomReached: () -> Unit) :
    RecyclerView.OnScrollListener() {
    private var messageSended = false
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (!recyclerView.canScrollVertically(1)) {
            if (!messageSended) {
                onBottomReached()
                messageSended = true
            }
        } else{
            messageSended = false
        }
    }
}
