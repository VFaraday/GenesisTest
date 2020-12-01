package com.bobryshev.genesistest.data.repository

import com.bobryshev.genesistest.data.db.IGitRepoDbDataSource
import com.bobryshev.genesistest.data.network.IGitRepoApiDateSource
import com.bobryshev.genesistest.data.network.Result
import com.bobryshev.genesistest.data.repository.domain.GitRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitRepoRepository @Inject constructor(
    private val gitRepoApiDataSource: IGitRepoApiDateSource,
    private val gitRepoDbDataSource: IGitRepoDbDataSource
) {

    suspend fun getGitRepoList() = gitRepoApiDataSource.getGitRepoList()

    suspend fun loadNextPage(): Flow<Result<List<GitRepo>>> = gitRepoApiDataSource.loadNextPageGitRepoList()

    suspend fun refresh(): Flow<Result<List<GitRepo>>> = gitRepoApiDataSource.refreshGitRepoList()

    suspend fun loadFavoriteGitRepo() = gitRepoDbDataSource.getFavoriteGitRepo()

    suspend fun addToFavoriteGitRepo(gitRepo: GitRepo) {
        gitRepoDbDataSource.addFavoriteRepo(gitRepo)
    }

    suspend fun removeFromFavoriteGitRepo(gitRepo: GitRepo) {
        gitRepoDbDataSource.deleteFavoriteRepo(gitRepo)
    }
}