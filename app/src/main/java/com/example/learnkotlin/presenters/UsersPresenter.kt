package com.example.learnkotlin.presenters

import com.example.learnkotlin.data.IGithubUsersRepo
import com.example.learnkotlin.data.models.GithubUser
import com.example.learnkotlin.ui.views.IScreens
import com.example.learnkotlin.ui.views.IUserListPresenter
import com.example.learnkotlin.ui.views.UserItemView
import com.example.learnkotlin.ui.views.UserView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UsersPresenter(val uiScheduler: Scheduler, val usersRepo:
IGithubUsersRepo, val router: Router, val screens: IScreens
) :
    MvpPresenter<UserView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let {view.loadAvatar(it)}
        }

    }
    val usersListPresenter = UsersListPresenter()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.user(user))
        }
    }
    fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
