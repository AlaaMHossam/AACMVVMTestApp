package com.lifetimecode.aacmvvmtestapp.data.datasources.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lifetimecode.aacmvvmtestapp.data.datasources.db.ArrivalDao
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival

@Database(entities = [Arrival::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun arrivalDao(): ArrivalDao
}