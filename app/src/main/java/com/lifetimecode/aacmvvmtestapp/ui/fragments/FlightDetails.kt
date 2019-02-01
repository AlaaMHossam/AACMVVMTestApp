package com.lifetimecode.aacmvvmtestapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lifetimecode.aacmvvmtestapp.R

class FlightDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_details)

        val safeArgs = FlightDetailsArgs.fromBundle(intent?.extras!!).flightData
        Log.d("FlightDetails", "onCreate : $safeArgs")

        // Fragment stuff
        /* arguments?.let {
             val safeArgs = FlightDetailsArgs.fromBundle(it)
             Log.d("FlightDetails", "onViewCreated : ${safeArgs.flightData}")
         }*/
    }
}
