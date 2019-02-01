package com.lifetimecode.aacmvvmtestapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import androidx.annotation.Nullable
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival


class MyDiffCallback : DiffUtil.Callback() {

    lateinit var oldArrivals: List<Arrival>
    lateinit var newArrivals: List<Arrival>

    fun diffCallback(newFlights: List<Arrival>, oldFlights: List<Arrival>) {
        this.newArrivals = newFlights
        this.oldArrivals = oldFlights
    }

    override fun getOldListSize(): Int {
        return oldArrivals.size
    }

    override fun getNewListSize(): Int {
        return newArrivals.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldArrivals[oldItemPosition].flightId == newArrivals[newItemPosition].flightId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldArrivals[oldItemPosition] == newArrivals[newItemPosition]
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}
