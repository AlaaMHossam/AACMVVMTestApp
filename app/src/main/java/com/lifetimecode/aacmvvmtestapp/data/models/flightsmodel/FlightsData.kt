package com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel

import com.google.gson.annotations.SerializedName

data class FlightsData(
    @SerializedName("result")
    var result: Result = Result()
)