package com.alexisdev.foodies

import android.app.Application
import com.alexisdev.foodies.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FoodiesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@FoodiesApplication)
            modules(koinModules)
        }
    }
}