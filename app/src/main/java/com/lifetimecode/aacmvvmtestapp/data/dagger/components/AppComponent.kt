package com.lifetimecode.aacmvvmtestapp.data.dagger.components

import com.lifetimecode.aacmvvmtestapp.App
import com.lifetimecode.aacmvvmtestapp.data.dagger.modules.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        NetworkModule::class,
        AndroidContributors::class,
        RepositoriesModule::class,
        ViewModelModule::class,
        RoomModule::class]
)

interface AppComponent : AndroidInjector<App> {
    override fun inject(app: App)
}