package com.example.learnkotlin.di

import com.example.learnkotlin.data.database.RetrofitGithubUsersRepo
import com.example.learnkotlin.data.internet.IDataSource
import com.example.learnkotlin.domen.data.IGithubUserRepository
import com.example.learnkotlin.domen.data.IUserCache
import com.example.learnkotlin.utils.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache:
    IUserCache): IGithubUserRepository = RetrofitGithubUsersRepo(api, networkStatus, cache)
}