package com.example.learnkotlin.data

import com.example.learnkotlin.data.models.GithubUser
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class GithubUsersRepo {

    private val users = listOf(
        GithubUser("user1"),
        GithubUser("user2"),
        GithubUser("user3"),
        GithubUser("user4"),
        GithubUser("user5")
    )

    fun getUsers(): Single<List<GithubUser>> {
        return Single.just(users).delay(5L, TimeUnit.SECONDS)
    }
    fun getUserByLogin(login: String): Single<GithubUser> {
        return Single.just(users[0]).delay(3L, TimeUnit.SECONDS)
    }
}
