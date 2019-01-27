package com.lifetimecode.aacmvvmtestapp.data.dagger.modules

import android.app.Application
import com.lifetimecode.aacmvvmtestapp.App
import com.lifetimecode.aacmvvmtestapp.ui.activities.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class AndroidContributors {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
}