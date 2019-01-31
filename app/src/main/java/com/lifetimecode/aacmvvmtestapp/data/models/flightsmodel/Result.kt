package com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("arrivals")
    val arrivals: List<Arrival> = listOf(),
    @SerializedName("departures")
    val departures: List<Departure> = listOf()
)