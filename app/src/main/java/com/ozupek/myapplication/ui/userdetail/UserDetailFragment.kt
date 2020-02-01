package com.ozupek.myapplication.ui.userdetail

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozupek.myapplication.R
import com.ozupek.myapplication.core.ui.BaseFragment
import com.ozupek.myapplication.ui.repositorylist.RepositoryAdapter
import kotlinx.android.synthetic.main.fragment_user_detail.*


const val KEY_REPOSITORY_MODEL = "KEY_REPOSITORY_MODEL"
class UserDetailFragment : BaseFragment(R.layout.fragment_user_detail), UserDetailView {
    private var adapter: RepositoryAdapter? = null

    override fun initViews() {
        adapter = RepositoryAdapter()
        rclRepo.layoutManager = LinearLayoutManager(fragmentContext)
        rclRepo.adapter = adapter
    }
}