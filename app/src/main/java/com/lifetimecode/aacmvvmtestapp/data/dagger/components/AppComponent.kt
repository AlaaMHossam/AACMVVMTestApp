package com.lifetimecode.aacmvvmtestapp.data.dagger.components

import com.lifetimecode.aacmvvmtestapp.App
import com.lifetimecode.aacmvvmtestapp.data.dagger.modules.AndroidContributors
import com.lifetimecode.aacmvvmtestapp.data.dagger.modules.NetworkModule
import com.lifetimecode.aacmvvmtestapp.data.dagger.modules.RepositoriesModule
import com.lifetimecode.aacmvvmtestapp.data.dagger.modules.ViewModelModule
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
        ViewModelModule::class]
)

interface AppComponent : AndroidInjector<App> {
    override fun inject(app: App)
}