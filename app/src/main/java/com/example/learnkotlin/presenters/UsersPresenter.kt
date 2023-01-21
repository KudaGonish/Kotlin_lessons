package com.example.learnkotlin.presenters

import com.example.learnkotlin.data.GithubUsersRepo
import com.example.learnkotlin.data.models.GithubUser
import com.example.learnkotlin.ui.AndroidScreens
import com.example.learnkotlin.ui.views.IUserListPresenter
import com.example.learnkotlin.ui.views.UserItemView
import com.example.learnkotlin.ui.views.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
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
    val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<GithubUser>> {
                fun loadData() {
                    val users = usersRepo.getUsers().blockingGet()
                    usersListPresenter.users.addAll(users)
                    usersListPresenter.itemClickListener = { itemView ->
                        router.navigateTo(AndroidScreens().userInfo(users[0].login))
                    }
                    viewState.updateList()


                }

                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }

                override fun onSuccess(t: List<GithubUser>) {
                    usersListPresenter.users.addAll(t)
                    usersListPresenter.itemClickListener = { itemView ->
                        router.navigateTo(AndroidScreens().userInfo(usersListPresenter.users[0].login))
                    }

                    viewState.updateList()
                }

                override fun onError(e: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
    override fun onDestroy() {
        disposables.clear()
    }
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
