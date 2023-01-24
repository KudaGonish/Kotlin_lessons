package com.example.learnkotlin.ui.views

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}
