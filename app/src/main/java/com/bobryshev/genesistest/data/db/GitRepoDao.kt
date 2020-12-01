package com.bobryshev.genesistest.data.db

import androidx.room.*
import com.bobryshev.genesistest.data.db.enteties.GitRepoEntity

@Dao
interface GitRepoDao {

    @Query("SELECT * FROM gitrepoentity")
    fun getFavoriteGitRepo(): List<GitRepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteRepo(gitRepo: GitRepoEntity)

    @Delete
    fun deleteFavoriteRepo(gitRepo: GitRepoEntity)
}