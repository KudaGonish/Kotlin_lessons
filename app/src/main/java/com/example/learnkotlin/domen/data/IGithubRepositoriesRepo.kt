package com.example.learnkotlin.domen.data

import com.example.learnkotlin.data.database.entity.GithubRepository
import com.example.learnkotlin.data.internet.models.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}
