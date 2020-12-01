package com.bobryshev.genesistest.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bobryshev.genesistest.data.network.Result
import com.bobryshev.genesistest.data.repository.GitRepoRepository
import com.bobryshev.genesistest.data.repository.domain.GitRepo
import com.bobryshev.genesistest.ui.util.Action
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val gitRepoRepository: GitRepoRepository): ViewModel() {

    private val action = MutableLiveData<Action>()

    init {
        action.value = Action.INIT
    }

    val listRepos: LiveData<Result<List<GitRepo>>> = action.switchMap { action ->
        liveData(Dispatchers.IO) {
            when(action) {
                Action.INIT -> gitRepoRepository.getGitRepoList()
                Action.REFRESH -> gitRepoRepository.refresh()
                Action.NEXT_PAGE -> gitRepoRepository.loadNextPage()
                else -> gitRepoRepository.refresh()
            }.collect { emit(it) }
        }
    }

    fun refresh() {
        action.value = Action.REFRESH
    }

    fun loadNextPage() {
        action.value = Action.NEXT_PAGE
    }

    val listFavorites: LiveData<List<GitRepo>> = liveData(Dispatchers.IO) {
        gitRepoRepository.loadFavoriteGitRepo().collect { emit(it) }
    }

    fun addToFavorite(gitRepo: GitRepo) {
        viewModelScope.launch(Dispatchers.IO) {
            gitRepoRepository.addToFavoriteGitRepo(gitRepo)
        }
    }

    fun removeFromFavorite(gitRepo: GitRepo) {
        viewModelScope.launch(Dispatchers.IO) {
            gitRepoRepository.removeFromFavoriteGitRepo(gitRepo)
        }
    }

}