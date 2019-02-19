package com.lifetimecode.aacmvvmtestapp.data.datasources.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival

@Dao
interface ArrivalDao {

    @Query("SELECT * FROM Arrival")
    fun getAllArrival(): List<Arrival>

    @Insert(onConflict = REPLACE)
    fun saveAllArrival(arrivalList: List<Arrival>?)
}