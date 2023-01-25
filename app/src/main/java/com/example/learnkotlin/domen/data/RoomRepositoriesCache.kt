package com.example.learnkotlin.domen.data

import com.example.learnkotlin.data.database.Database


import com.example.learnkotlin.data.database.entity.GithubRepository
import com.example.learnkotlin.data.database.entity.RoomGithubRepository
import com.example.learnkotlin.data.internet.models.GithubUser

class RoomRepositoriesCache: IRepositoriesCache {
    override fun setImage(user: GithubUser, repositories: List<GithubRepository>, db: Database): List<GithubRepository> {
        val roomUser = user.login?.let {
            db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
        val roomRepos = repositories.map {
            RoomGithubRepository(it.id ?: "", it.name ?: "", it.forksCount ?: 0,
                roomUser.id) }
        db.repositoryDao.insert(roomRepos)
        return repositories
    }

}