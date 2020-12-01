package com.bobryshev.genesistest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bobryshev.genesistest.data.db.enteties.GitRepoEntity

@Database(entities = [GitRepoEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun gitRepoDao(): GitRepoDao
}