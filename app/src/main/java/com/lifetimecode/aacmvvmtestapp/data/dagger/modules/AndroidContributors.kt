package com.lifetimecode.aacmvvmtestapp.data.dagger.modules

import com.lifetimecode.aacmvvmtestapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidContributors {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
}