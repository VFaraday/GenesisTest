package com.bobryshev.genesistest.data.network

import com.bobryshev.genesistest.data.repository.domain.GitRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GitRepoApiDataSource @Inject constructor(
    private val apiGithub: GithubApi): IGitRepoApiDateSource {

    private var page1: Int = 1
    private var page2: Int = 2

    private val list: MutableList<GitRepo> = mutableListOf()

    override suspend fun getGitRepoList() = flow {
        try {
            coroutineScope {
                val thread1 = async { apiGithub.getRepos("blog", page1, 15) }
                val thread2 = async { apiGithub.getRepos("blog", page2, 15) }
//                thread1.await().run {
//                    if(isSuccessful) {
//                        list.addAll(body()?.items.orEmpty())
//                    } else {
//                        page1--
//                    }
//                }
                list.addAll(thread1.await().body()?.items.orEmpty() + thread2.await().body()?.items.orEmpty())
            }
            emit(Result.Success(list))
        } catch (e: Exception) {
            emit(Result.Failure(e.message))
        }
    }

    override suspend fun refreshGitRepoList(): Flow<Result<List<GitRepo>>> {
        page1 = 1
        page2 = 2
        list.clear()
        return getGitRepoList()
    }

    override suspend fun loadNextPageGitRepoList(): Flow<Result<List<GitRepo>>> {
        page1++
        page2++
        return getGitRepoList()
    }
}