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

    suspend fun getFlights(handler: CoroutineExceptionHandler, saveDB: Boolean) =
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext + handler) {
            val data = flightsRepository.flightsData(saveDB)
            CoroutineScope(Dispatchers.Main).launch {
                flightsLiveData.value = data
            }
        }

    fun getArrivalDB(): List<Arrival> = runBlocking {
        flightsRepository.getFlightsDB()
    }

    fun saveFlightsDB() {
        CoroutineScope(Dispatchers.IO).launch {
            flightsRepository.flightsData(true)
        }
    }
}