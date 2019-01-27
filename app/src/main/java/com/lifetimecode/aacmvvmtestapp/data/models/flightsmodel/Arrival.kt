package com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Arrival(
    @PrimaryKey
    @SerializedName("flight_id")
    var flightId: String = "",
    @SerializedName("flight_no")
    var flightNo: String = "",
    @SerializedName("airline_name")
    var airlineName: String = "",
    @SerializedName("time")
    var time: String = "",
    @SerializedName("city_from")
    var cityFrom: String = "",
    @SerializedName("status")
    var status: String = ""
)