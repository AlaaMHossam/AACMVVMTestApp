package com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel

import com.google.gson.annotations.SerializedName

data class Departure(
    @SerializedName("flight_id")
    var flightId: String = "",
    @SerializedName("flight_no")
    var flightNo: String = "",
    @SerializedName("airline_name")
    var airlineName: String = "",
    @SerializedName("time")
    var time: String = "",
    @SerializedName("city_to")
    var cityTo: String = "",
    @SerializedName("city_from")
    var cityFrom: String = "",
    @SerializedName("status")
    var status: String = ""
)