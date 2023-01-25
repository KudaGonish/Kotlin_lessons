package com.example.learnkotlin.domen.data

import com.example.learnkotlin.data.database.Database
import com.example.learnkotlin.data.internet.models.GithubUser

interface IUserCache {
    fun setImage(users: List<GithubUser>, db: Database) : List<GithubUser>
}