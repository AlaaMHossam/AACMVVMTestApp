package com.lifetimecode.aacmvvmtestapp.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import com.lifetimecode.aacmvvmtestapp.data.repositories.FlightsRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class FlightsViewModel
@Inject
constructor(private var flightsRepository: FlightsRepository) : ViewModel() {

    var flightsLiveData: MutableLiveData<FlightsData> = MutableLiveData()

    suspend fun getFlights(handler: CoroutineExceptionHandler) =
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val data = flightsRepository.flightsData(false)
            CoroutineScope(Dispatchers.Main).launch {
                flightsLiveData.value = data
            }

        }

    suspend fun getFlightsUpdateDB(handler: CoroutineExceptionHandler) =
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val data = flightsRepository.flightsData(true)
            CoroutineScope(Dispatchers.Main).launch {
                flightsLiveData.value = data
            }
        }

    fun getFlightsDB(): List<Arrival> = runBlocking {
        flightsRepository.getFlightsDBAsync().await()
    }

    fun saveFlightsDB() {
        CoroutineScope(Dispatchers.IO).launch {
            flightsRepository.flightsData(true)
        }
    }
}