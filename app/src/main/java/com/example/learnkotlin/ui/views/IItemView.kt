package com.example.learnkotlin.ui.views

interface IItemView {
    var pos: Int
}
interface UserItemView: IItemView {
    fun setLogin(text: String)
}
