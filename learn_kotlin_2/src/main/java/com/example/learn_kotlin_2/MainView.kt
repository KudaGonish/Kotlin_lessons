package com.example.learn_kotlin_2

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView: MvpView {
    fun setButtonOneText(text: String)
    fun setButtonTwoText(text: String)
    fun setButtonThreeText(text: String)
}


