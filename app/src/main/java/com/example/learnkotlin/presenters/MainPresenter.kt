package com.example.learnkotlin.presenters

import com.example.learnkotlin.models.CountersModel
import com.example.learnkotlin.ui.views.MainView

class MainPresenter(private val view: MainView) {
    private val model = CountersModel()

    fun counterButton1Click() {
        val nextValue = model.next(0)
        view.setButton1Text(0, nextValue.toString())
    }

    fun counterButton2Click() {
        val nextValue = model.next(1)
        view.setButton2Text(1, nextValue.toString())
    }
    fun counterButton3Click() {
        val nextValue = model.next(2)
        view.setButton3Text(2, nextValue.toString())
    }
}
