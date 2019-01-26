package com.lifetimecode.aacmvvmtestapp

import android.app.Application
import com.lifetimecode.aacmvvmtestapp.data.dagger.components.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().build()
    }
}