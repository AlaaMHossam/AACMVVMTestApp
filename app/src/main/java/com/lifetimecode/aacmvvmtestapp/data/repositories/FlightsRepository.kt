package com.lifetimecode.aacmvvmtestapp.data.repositories

import com.lifetimecode.aacmvvmtestapp.data.dagger.modules.AppExecutors
import com.lifetimecode.aacmvvmtestapp.data.datasources.db.ArrivalDao
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.Webservice
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlightsRepository
@Inject
constructor(
    private val webservice: Webservice,
    private val arrivalDao: ArrivalDao,
    private val appExecutors: AppExecutors
) {
    suspend fun flightsData(saveDB: Boolean): FlightsData {
        val flightsData = webservice.getFlightsAsync().await()
        if (saveDB) arrivalDao.saveAllArrival(flightsData.result.arrivals)
        return flightsData
    }

    fun getFlightsDBAsync(): Deferred<List<Arrival>> =
        CoroutineScope(Dispatchers.IO).async {
            arrivalDao.getAllArrival()
        }
}