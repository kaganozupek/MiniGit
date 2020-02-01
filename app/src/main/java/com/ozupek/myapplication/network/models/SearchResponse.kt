package com.ozupek.myapplication.network.models
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

data class OwnerModel(
   val login: String,
   val id: Int,
   val avatar_url: String?
)