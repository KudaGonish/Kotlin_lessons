package com.example.learnkotlin.di

import com.example.learnkotlin.presenters.MainPresenter
import com.example.learnkotlin.presenters.UserPresenter
import com.example.learnkotlin.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UserPresenter)
}
