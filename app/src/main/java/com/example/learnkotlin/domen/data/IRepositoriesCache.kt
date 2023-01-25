package com.example.learnkotlin.domen.data

import com.example.learnkotlin.data.database.Database
import com.example.learnkotlin.data.database.entity.GithubRepository
import com.example.learnkotlin.data.internet.models.GithubUser
import com.example.learnkotlin.data.database.entity.RoomGithubRepository
interface IRepositoriesCache {
    fun setImage(user: GithubUser, repositories: List<GithubRepository>, db: Database) : List<GithubRepository>
}