package com.example.learnkotlin.presenters

import com.example.learnkotlin.ui.views.IScreens
import com.example.learnkotlin.ui.views.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}

