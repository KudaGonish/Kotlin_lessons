package com.example.learnkotlin.di

import androidx.room.Room
import com.example.learnkotlin.App
import com.example.learnkotlin.data.database.Database
import com.example.learnkotlin.domen.data.IUserCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {
    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app,
        Database::class.java, Database.DB_NAME)
        .build()
    @Singleton
    @Provides
    fun usersCache(database: Database): IUserCache =
        RoomGithubUsersCache(database)
}
