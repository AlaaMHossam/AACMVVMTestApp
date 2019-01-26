package com.lifetimecode.aacmvvmtestapp.data.datasources.network.networkcalls

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.Webservice
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FlightsNetworkCall(webservice: Webservice) {

    var getFlightsCall: Call<FlightsData> = webservice.getFlights()

    fun getFlights(): LiveData<FlightsData> {

        val data: MutableLiveData<FlightsData>? = null

        getFlightsCall.enqueue(object : Callback<FlightsData> {

            override fun onResponse(call: Call<FlightsData>, response: Response<FlightsData>) {
                data?.postValue(response.body())
            }

            override fun onFailure(call: Call<FlightsData>, t: Throwable) {
            }
        })

        return data!!
    }
}