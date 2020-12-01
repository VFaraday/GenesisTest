package com.bobryshev.genesistest.di

import android.content.Context
import androidx.room.Room
import com.bobryshev.genesistest.data.db.AppDataBase
import com.bobryshev.genesistest.data.db.GitRepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DbModule {

    @Provides
    fun provideGitRepoDao(appDataBase: AppDataBase): GitRepoDao {
        return appDataBase.gitRepoDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(appContext, AppDataBase::class.java, "GitRepo").build()
    }
}