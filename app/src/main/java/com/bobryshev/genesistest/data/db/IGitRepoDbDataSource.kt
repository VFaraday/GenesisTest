package com.bobryshev.genesistest.data.db

import com.bobryshev.genesistest.data.repository.domain.GitRepo
import kotlinx.coroutines.flow.Flow

interface IGitRepoDbDataSource {

    suspend fun getFavoriteGitRepo(): Flow<List<GitRepo>>

    suspend fun addFavoriteRepo(gitRepo: GitRepo)

    suspend fun deleteFavoriteRepo(gitRepo: GitRepo)
}