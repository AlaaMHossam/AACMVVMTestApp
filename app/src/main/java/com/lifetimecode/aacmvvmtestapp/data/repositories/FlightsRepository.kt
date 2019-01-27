package com.lifetimecode.aacmvvmtestapp.data.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.lifetimecode.aacmvvmtestapp.data.dagger.modules.AppExecutors
import com.lifetimecode.aacmvvmtestapp.data.datasources.db.ArrivalDao
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.Webservice
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.networkcalls.FlightsNetworkCall
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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

    fun getFlightNetwork(): MutableLiveData<FlightsData> =
        FlightsNetworkCall(webservice, appExecutors).getFlights(null)

    fun getFlightNetworkUpdateDB(): MutableLiveData<FlightsData> =
        FlightsNetworkCall(webservice, appExecutors).getFlights(arrivalDao)



    fun test(){

        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            arrivalDao.getAllArrival()
        }
    }


    fun getArrivalDataDB(): (List<Arrival>) -> List<Arrival> {
        return getDBStuff(arrivalDao, fun(data: List<Arrival>): List<Arrival> = data)
    }

    private fun getDBStuff(

        arrivalDao: ArrivalDao,
        function: (List<Arrival>) -> List<Arrival>
    ): (List<Arrival>) -> List<Arrival> {
        appExecutors.diskIO().execute {
            function(
                arrivalDao.getAllArrival()
            )
        }
        return function
    }
}