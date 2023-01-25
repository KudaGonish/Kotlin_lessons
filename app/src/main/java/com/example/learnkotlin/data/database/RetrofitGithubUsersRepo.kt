package com.example.learnkotlin.data.database


import com.example.learnkotlin.data.internet.IDataSource
import com.example.learnkotlin.data.internet.models.GithubUser
import com.example.learnkotlin.utils.INetworkStatus
import com.example.learnkotlin.domen.data.IGithubUserRepository
import com.example.learnkotlin.domen.data.RoomUserCache
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

//Практическое задание 1 - вытащить кэширование в отдельный класс RoomUserCache и внедрить его сюда через интерфейс IUserCache
class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val db: Database,
    val dbCache: RoomUserCache
) : IGithubUserRepository {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        dbCache.setImage(users, db)
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GithubUser(
                        roomUser.id, roomUser.login, roomUser.avatarUrl,
                        roomUser.reposUrl
                    )
                }
            }
        }
    }.subscribeOn(Schedulers.io())

}
