package com.bobryshev.genesistest.data.repository.domain

import com.google.gson.annotations.SerializedName

data class GitRepoList(
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<GitRepo>
)
