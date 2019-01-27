package com.lifetimecode.aacmvvmtestapp

import android.app.Activity
import android.app.Application
import com.lifetimecode.aacmvvmtestapp.data.dagger.components.DaggerAppComponent
import com.lifetimecode.aacmvvmtestapp.data.dagger.modules.NetworkModule
import com.lifetimecode.aacmvvmtestapp.data.dagger.modules.RepositoriesModule
import com.lifetimecode.aacmvvmtestapp.data.dagger.modules.RoomModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .roomModule(RoomModule(this))
            .networkModule(NetworkModule)
            .repositoriesModule(RepositoriesModule)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}