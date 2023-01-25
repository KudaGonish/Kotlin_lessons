package com.example.learnkotlin.domen.data

import com.example.learnkotlin.data.database.Database
import com.example.learnkotlin.data.database.entity.RoomGithubUser
import com.example.learnkotlin.data.internet.models.GithubUser
import io.reactivex.rxjava3.core.Single

class RoomUserCache: IUserCache {
    override fun setImage(users: List<GithubUser>, db: Database): List<GithubUser> {
        val roomUsers = users.map { user ->
            RoomGithubUser(
                user.id ?: "", user.login ?: "", user.avatarUrl ?: "",
                user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
        return users
    }
}