package com.example.learnkotlin.di

import com.example.learnkotlin.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}
