package com.example.learnkotlin.data.database

import com.example.learnkotlin.data.database.entity.GithubRepository
import com.example.learnkotlin.data.internet.IDataSource
import com.example.learnkotlin.data.internet.models.GithubUser
import com.example.learnkotlin.domen.data.IGithubRepositoriesRepo
import com.example.learnkotlin.domen.data.RoomRepositoriesCache
import com.example.learnkotlin.utils.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

//Практическое задание 1 - вытащить кэширование в отдельный класс RoomRepositoriesCache и внедрить его сюда через интерфейс IRepositoriesCache
class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val networkStatus:
    INetworkStatus,
    val db: Database,
    val dbCache: RoomRepositoriesCache
) : IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let {
                    api.getRepositories(user.reposUrl)
                        .flatMap { repositories ->
                            Single.fromCallable {
                                dbCache.setImage(user, repositories, db)
                            }
                        }
                } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
                    .subscribeOn(
                        Schedulers.io()
                    )
            } else {
                Single.fromCallable {
                    val roomUser = user.login?.let { db.userDao.findByLogin(it) }
                        ?: throw RuntimeException("No such user in cache")
                    db.repositoryDao.findForUser(roomUser.id).map {
                        GithubRepository(it.id, it.name, it.forksCount)
                    }
                }
            }
        }.subscribeOn(Schedulers.io())
}