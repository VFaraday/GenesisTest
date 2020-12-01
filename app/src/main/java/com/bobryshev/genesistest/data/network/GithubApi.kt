package com.bobryshev.genesistest.data.network

import com.bobryshev.genesistest.data.repository.domain.GitRepoList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    suspend fun getRepos(@Query("q") q: String,
                         @Query("page") page: Int,
                         @Query("per_page") perPage: Int): Response<GitRepoList>
}