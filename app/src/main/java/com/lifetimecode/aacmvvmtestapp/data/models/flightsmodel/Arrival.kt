package com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Arrival(
    @PrimaryKey
    @SerializedName("flight_id")
    val flightId: String = "",
    @SerializedName("flight_no")
    val flightNo: String = "",
    @SerializedName("airline_name")
    val airlineName: String = "",
    @SerializedName("time")
    val time: String = "",
    @SerializedName("city_from")
    val cityFrom: String = "",
    @SerializedName("status")
    val status: String = ""
): Parcelable