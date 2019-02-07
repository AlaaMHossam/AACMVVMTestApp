package com.lifetimecode.aacmvvmtestapp.data.dagger.modules

import com.lifetimecode.aacmvvmtestapp.ui.activities.MainActivity
import com.lifetimecode.aacmvvmtestapp.ui.fragments.HomeFragment
import com.lifetimecode.aacmvvmtestapp.ui.fragments.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidContributors {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun notificationsFragment(): NotificationsFragment
}