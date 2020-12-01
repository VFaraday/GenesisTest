package com.bobryshev.genesistest.data.db

import com.bobryshev.genesistest.data.db.enteties.toGitRepo
import com.bobryshev.genesistest.data.repository.domain.GitRepo
import com.bobryshev.genesistest.data.repository.domain.toGitEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GitRepoDbDataSource @Inject constructor(private val gitRepoDao: GitRepoDao): IGitRepoDbDataSource {

    override suspend fun getFavoriteGitRepo(): Flow<List<GitRepo>> = flow {
        val favoriteList = gitRepoDao.getFavoriteGitRepo().map { entity ->
            entity.toGitRepo()
        }
        emit(favoriteList)
    }

    override suspend fun addFavoriteRepo(gitRepo: GitRepo) {
        gitRepoDao.insertFavoriteRepo(gitRepo.toGitEntity())
    }

    override suspend fun deleteFavoriteRepo(gitRepo: GitRepo) {
        gitRepoDao.deleteFavoriteRepo(gitRepo.toGitEntity())
    }
}