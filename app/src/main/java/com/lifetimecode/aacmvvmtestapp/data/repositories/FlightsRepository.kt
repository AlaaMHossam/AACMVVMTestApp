package com.lifetimecode.aacmvvmtestapp.data.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getFlightNetwork(): MutableLiveData<FlightsData> =
        FlightsNetworkCall(webservice, appExecutors).getFlights(null)

    fun getFlightNetworkUpdateDB(): MutableLiveData<FlightsData> =
        FlightsNetworkCall(webservice, appExecutors).getFlights(arrivalDao)

    @WorkerThread
    suspend fun getdb(): LiveData<List<Arrival>>{
       return arrivalDao.getAllArrival()
    }

  /*  fun getArrivalDataDB(): LiveData<List<Arrival>>? {

        var datareturn: LiveData<List<Arrival>>? = null
         getDBStuff(arrivalDao,fun(data:LiveData<List<Arrival>>) {
           datareturn = data

        })

      return datareturn

    }

    private fun getDBStuff(arrivalDao: ArrivalDao, function: (LiveData<List<Arrival>>) -> Unit) {
        appExecutors.diskIO().execute {
            function(
                arrivalDao.getAllArrival()
            )
        }
    }*/
}