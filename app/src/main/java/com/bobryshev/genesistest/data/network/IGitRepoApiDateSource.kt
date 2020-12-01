package com.bobryshev.genesistest.data.network

import com.bobryshev.genesistest.data.repository.domain.GitRepo
import kotlinx.coroutines.flow.Flow

interface IGitRepoApiDateSource {

    suspend fun getGitRepoList(): Flow<Result<List<GitRepo>>>

    suspend fun refreshGitRepoList(): Flow<Result<List<GitRepo>>>

    suspend fun loadNextPageGitRepoList(): Flow<Result<List<GitRepo>>>
}