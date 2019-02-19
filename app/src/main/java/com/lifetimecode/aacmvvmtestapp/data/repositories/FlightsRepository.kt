package com.lifetimecode.aacmvvmtestapp.data.repositories

import com.lifetimecode.aacmvvmtestapp.data.datasources.dao.ArrivalDao
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.Webservice
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlightsRepository
@Inject
constructor(
    private val webservice: Webservice,
    private val arrivalDao: ArrivalDao
) {
    suspend fun flightsData(saveDB: Boolean): FlightsData {
        val flightsData = webservice.getFlightsAsync().await()
        if (saveDB) arrivalDao.saveAllArrival(flightsData.result.arrivals)
        return flightsData
    }

    suspend fun getFlightsDB(): List<Arrival> =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            arrivalDao.getAllArrival()
        }
}