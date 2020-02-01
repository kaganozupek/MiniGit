package com.ozupek.myapplication.network.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozupek.myapplication.R
import com.ozupek.myapplication.network.NetworkManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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
        NetworkManager.getApi().searchRepositories(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter?.bindData(it.items)
            }, {

            }).addTo(compositeDisposable)
    }


}

abstract class BaseFragment(private val viewID: Int) : Fragment() {

    lateinit var fragmentContext: Context
    protected var compositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(viewID, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    abstract fun initViews()
}