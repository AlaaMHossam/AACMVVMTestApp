package com.lifetimecode.aacmvvmtestapp.data.datasources.network.networkcalls

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.lifetimecode.aacmvvmtestapp.data.dagger.modules.AppExecutors
import com.lifetimecode.aacmvvmtestapp.data.datasources.db.ArrivalDao
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.Webservice
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FlightsNetworkCall(
    webservice: Webservice,
    private val appExecutors: AppExecutors
) {

    private var getFlightsCall: Call<FlightsData> = webservice.getFlights()

    fun getFlights(arrivalDao: ArrivalDao?): MutableLiveData<FlightsData> {

        val data = MutableLiveData<FlightsData>()

        getFlightsCall.enqueue(object : Callback<FlightsData> {

            override fun onResponse(call: Call<FlightsData>, response: Response<FlightsData>) {
                when (response.code()) {
                    200 -> {
                        appExecutors.diskIO().execute {
                            arrivalDao?.saveAllArrival(response.body()?.result?.arrivals as MutableList<Arrival>?)

                        }
                        data.value = response.body()
                    }
                }
            }

            override fun onFailure(call: Call<FlightsData>, t: Throwable) {
            }
        })
        return data
    }

}