package com.example.learnkotlin.domen.data

import com.example.learnkotlin.data.internet.models.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUserRepository {
    fun getUsers(): Single<List<GithubUser>>
}