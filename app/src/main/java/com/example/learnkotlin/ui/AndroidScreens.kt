package com.example.learnkotlin.ui

import com.example.learnkotlin.ui.fragments.UserInfoFragment
import com.example.learnkotlin.ui.fragments.UsersFragment
import com.example.learnkotlin.ui.views.IScreens
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun user() = FragmentScreen { UsersFragment.newInstance() }
    override fun userInfo(login: String) = FragmentScreen { UserInfoFragment.newInstance(login) }
}
