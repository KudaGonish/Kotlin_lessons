package com.example.learnkotlin.ui.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserInfoView: MvpView {

    fun showLogin(text: String)
}