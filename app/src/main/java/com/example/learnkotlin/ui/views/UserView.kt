package com.example.learnkotlin.ui.views

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView: MvpView {
    fun init()
    fun updateList()
    fun setLogin(login: String)
}
