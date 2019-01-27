package com.lifetimecode.aacmvvmtestapp.data.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import com.lifetimecode.aacmvvmtestapp.data.repositories.FlightsRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class FlightsViewModel
@Inject
constructor(private var flightsRepository: FlightsRepository) : ViewModel() {



    fun getFlightsNetwork(): MutableLiveData<FlightsData> = flightsRepository.getFlightNetwork()

    fun getFlightsNetworkUpdateDB(): MutableLiveData<FlightsData> = flightsRepository.getFlightNetworkUpdateDB()

    /*fun getArrivalDB(): List<Arrival> {
        return flightsRepository.test()
    }*/

    suspend fun test() = coroutineScope{

        return@coroutineScope suspend {
            val a = async {
                getFlightsNetwork()
            }


            val aResult = a.await()
            Log.d("MainActivity", "test : $aResult")
        }

        /* val scope = CoroutineScope(Dispatchers.IO)

         scope.launch {
             arrivalDao.getAllArrival()
         }*/
    }

    fun start() {

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch { test() }
    }


//    fun saveArrivalDB() = flightsRepository.saveArrivalDB()
}