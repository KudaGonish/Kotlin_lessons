package com.example.learnkotlin.ui

import com.example.learnkotlin.ui.fragments.UserInfoFragment
import com.example.learnkotlin.ui.fragments.UsersFragment
import com.example.learnkotlin.ui.views.IScreens
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun userInfo(userLogin: String) = FragmentScreen { UserInfoFragment.newInstance(userLogin) }
}