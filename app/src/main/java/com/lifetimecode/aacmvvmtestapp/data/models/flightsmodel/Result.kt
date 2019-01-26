package com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("arrivals")
    var arrivals: List<Arrival> = listOf(),
    @SerializedName("departures")
    var departures: List<Departure> = listOf()
)