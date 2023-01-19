package com.example.learn_kotlin_2

import com.example.learnkotlin.models.CountersModel
import moxy.MvpPresenter


class MainPresenter(val model: CountersModel): MvpPresenter<MainView>() {
    fun counterOneClick() {
        val nextValue = model.next(0)
        viewState.setButtonOneText(nextValue.toString())
    }
    fun counterTwoClick() {
        val nextValue = model.next(1)
        viewState.setButtonTwoText(nextValue.toString())
    }
    fun counterThreeClick() {
        val nextValue = model.next(2)
        viewState.setButtonThreeText(nextValue.toString())
    }
}
