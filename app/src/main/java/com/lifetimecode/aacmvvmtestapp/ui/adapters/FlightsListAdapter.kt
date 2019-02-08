package com.lifetimecode.aacmvvmtestapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lifetimecode.aacmvvmtestapp.R
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.databinding.FlightsListItemBinding
import com.lifetimecode.aacmvvmtestapp.ui.fragments.HomeFragment
import com.lifetimecode.aacmvvmtestapp.utilities.DiffCallBacks

class FlightsListAdapter : ListAdapter<Arrival,
        FlightsListAdapter.FlightsViewHolder>(DiffCallBacks.ArrivalsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val flightsListItemBinding: FlightsListItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.flights_list_item, parent, false)

        return (FlightsViewHolder(flightsListItemBinding))
    }

    override fun onBindViewHolder(holder: FlightsViewHolder, position: Int) = holder.bind(getItem(position))

    inner class FlightsViewHolder(private val flightsListItemBinding: FlightsListItemBinding) :
        RecyclerView.ViewHolder(flightsListItemBinding.root) {

        fun bind(arrival: Arrival) {
            flightsListItemBinding.flightsListItem = arrival
            flightsListItemBinding.position = adapterPosition
            flightsListItemBinding.clickHandler = HomeFragment()
            flightsListItemBinding.executePendingBindings()
        }
    }
}