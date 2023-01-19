package com.example.learnkotlin.presenters

import com.example.learnkotlin.data.GithubUsersRepo
import com.example.learnkotlin.data.models.GithubUser
import com.example.learnkotlin.ui.AndroidScreens
import com.example.learnkotlin.ui.views.IUserListPresenter
import com.example.learnkotlin.ui.views.UserItemView
import com.example.learnkotlin.ui.views.UsersView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(
    val usersRepo: GithubUsersRepo,
    val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(AndroidScreens().userInfo(users[itemView.pos].login))
        }
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}