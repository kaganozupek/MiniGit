package com.ozupek.myapplication.ui.repositorylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.ozupek.myapplication.R
import com.ozupek.myapplication.network.models.RepositoryModel
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter(
    val onItemClickListener: ((RepositoryModel)-> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var datasource: ArrayList<RepositoryModel> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return datasource.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = datasource[position]
        holder.itemView.tvTitle.text = item.name
        holder.itemView.tvDescription.text = item.description
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }

    fun bindData(items: ArrayList<RepositoryModel>) {
        datasource.addAll(items)
        this.notifyDataSetChanged()
    }

    fun clearData() {
        datasource.clear()
        this.notifyDataSetChanged()
    }

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}