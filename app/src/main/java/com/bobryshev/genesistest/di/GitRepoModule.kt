package com.bobryshev.genesistest.di

import com.bobryshev.genesistest.data.db.GitRepoDbDataSource
import com.bobryshev.genesistest.data.db.IGitRepoDbDataSource
import com.bobryshev.genesistest.data.network.GitRepoApiDataSource
import com.bobryshev.genesistest.data.network.IGitRepoApiDateSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class GitRepoApiModule {

    @Singleton
    @Binds
    abstract fun bindGitRepoApiDataSource(gitRepoApiDataSource: GitRepoApiDataSource): IGitRepoApiDateSource
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class GitRepoDbModule {

    @Singleton
    @Binds
    abstract fun bindGitRepoDbDataSource(gitRepoApiDataSource: GitRepoDbDataSource): IGitRepoDbDataSource
}