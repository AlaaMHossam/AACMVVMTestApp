package com.lifetimecode.aacmvvmtestapp.utilities

import androidx.recyclerview.widget.DiffUtil
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival

class DiffCallBacks {

    class ArrivalsDiffCallback : DiffUtil.ItemCallback<Arrival>() {
        override fun areItemsTheSame(oldItem: Arrival, newItem: Arrival): Boolean = oldItem.flightId == newItem.flightId
        override fun areContentsTheSame(oldItem: Arrival, newItem: Arrival): Boolean = oldItem == newItem
    }
}