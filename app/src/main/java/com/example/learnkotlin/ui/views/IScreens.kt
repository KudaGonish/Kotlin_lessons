package com.example.learnkotlin.ui.views

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun user(): Screen
    fun userInfo(login: String): Screen
}
