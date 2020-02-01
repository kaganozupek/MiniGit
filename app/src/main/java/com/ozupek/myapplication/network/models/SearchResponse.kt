package com.ozupek.myapplication.network.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//Response from search endpoint
data class SearchResponse (
   val total_count: Int,
   val items: ArrayList<RepositoryModel>
)


data class RepositoryModel(
   val id: Int,
   val name: String,
   val full_name: String,
   val owner: OwnerModel,
   val description: String?,
   val language: String?,
   val stargazers_count: Int,
   val forks_count: Int,
   val score: Double
)
@Parcelize
data class OwnerModel(
   val login: String,
   val id: Int,
   val avatar_url: String?
) : Parcelable