package com.example.learnkotlin.presenters

import com.example.learnkotlin.data.GithubUsersRepo
import com.example.learnkotlin.ui.views.UserInfoView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserInfoPresenter(
    val userLogin: String? = null,
    private val githubUsersRepo: GithubUsersRepo,
    val router: Router
) : MvpPresenter<UserInfoView>() {

    override fun onFirstViewAttach() {
        val currentUser = githubUsersRepo.getUsers().firstOrNull()
        currentUser?.let { viewState.showLogin(it.login) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}