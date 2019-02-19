package com.lifetimecode.aacmvvmtestapp.data.dagger.modules

import androidx.room.Room
import com.lifetimecode.aacmvvmtestapp.App
import com.lifetimecode.aacmvvmtestapp.data.datasources.database.Database
import com.lifetimecode.aacmvvmtestapp.data.datasources.dao.ArrivalDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(private val app: App) {

    @Singleton
    @Provides
    fun providesAppDatabase(): Database = Room.databaseBuilder(app, Database::class.java, "app_db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providesArrivalDao(database: Database): ArrivalDao = database.arrivalDao()

}