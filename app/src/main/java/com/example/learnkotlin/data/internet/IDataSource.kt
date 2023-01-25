package com.example.learnkotlin.data.internet

import com.example.learnkotlin.data.database.entity.GithubRepository
import com.example.learnkotlin.data.internet.models.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
    fun getRepositories(url: String): Single<List<GithubRepository>>
}
