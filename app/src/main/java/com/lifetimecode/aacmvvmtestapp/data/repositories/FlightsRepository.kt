package com.lifetimecode.aacmvvmtestapp.data.repositories

import androidx.lifecycle.LiveData
import com.lifetimecode.aacmvvmtestapp.data.dagger.modules.AppExecutors
import com.lifetimecode.aacmvvmtestapp.data.datasources.db.ArrivalDao
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.Webservice
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.networkcalls.FlightsNetworkCall
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
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

    fun getFlightNetwork(): LiveData<FlightsData> = FlightsNetworkCall(webservice).getFlights()

    fun getArrivalDB(): LiveData<List<Arrival>> = arrivalDao.getAllArrival()

    fun saveArrivalDB() {
        appExecutors.diskIO().execute {
            arrivalDao.saveAllArrival(getFlightNetwork().value?.result?.arrivals)
        }
    }
}