package com.example.learnkotlin.presenters

import com.example.learnkotlin.ui.views.IScreens
import com.example.learnkotlin.ui.views.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) :
    MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.user())
    }
    fun backClicked() {
        router.exit()
    }
}


