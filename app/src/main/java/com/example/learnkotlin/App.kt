package com.example.learnkotlin

import android.app.Application
import com.example.learnkotlin.di.AppComponent
import com.example.learnkotlin.di.AppModule
import com.example.learnkotlin.di.DaggerAppComponent
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
    }
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
