package com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel

import com.google.gson.annotations.SerializedName

data class Departure(
    @SerializedName("flight_id")
    val flightId: String = "",
    @SerializedName("flight_no")
    val flightNo: String = "",
    @SerializedName("airline_name")
    val airlineName: String = "",
    @SerializedName("time")
    val time: String = "",
    @SerializedName("city_to")
    val cityTo: String = "",
    @SerializedName("city_from")
    val cityFrom: String = "",
    @SerializedName("status")
    val status: String = ""
)