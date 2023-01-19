package com.example.learnkotlin.ui.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UsersView: MvpView {
    fun init()
    fun updateList()
}
