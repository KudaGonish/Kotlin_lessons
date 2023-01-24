package com.example.learnkotlin.presenters

import com.example.learnkotlin.data.GithubUsersRepo
import com.example.learnkotlin.data.models.GithubUser
import com.example.learnkotlin.ui.views.UserInfoView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class UserInfoPresenter(
    val userLogin: String? = null,
    private val githubUsersRepo: GithubUsersRepo,
    val router: Router
) : MvpPresenter<UserInfoView>() {

    val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        if (userLogin != null) {
            githubUsersRepo
                .getUserByLogin(userLogin)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<GithubUser> {
                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d)
                    }

                    override fun onSuccess(t: GithubUser) {
                        t.let { viewState.showLogin(it.login!!) }
                        t.login
                    }

                    override fun onError(e: Throwable) {
                    }
                })
        }
    }

fun backPressed(): Boolean {
    router.exit()
    return true
}

override fun onDestroy() {
    disposables.clear()
}
}