package com.lifetimecode.aacmvvmtestapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival

class Diff(private var newArrivals: List<Arrival>, private var oldArrivals: List<Arrival>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldArrivals.size
    }

    override fun getNewListSize(): Int {
        return newArrivals.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldArrivals[oldItemPosition].flightId === newArrivals[newItemPosition].flightId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldArrivals[oldItemPosition] == newArrivals[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}

