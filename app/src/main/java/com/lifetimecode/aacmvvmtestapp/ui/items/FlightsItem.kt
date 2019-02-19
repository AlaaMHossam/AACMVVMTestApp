package com.lifetimecode.aacmvvmtestapp.ui.items

import com.lifetimecode.aacmvvmtestapp.R
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.databinding.FlightsListItemBinding
import com.xwray.groupie.databinding.BindableItem

class FlightsItem(private val arrival: Arrival) : BindableItem<FlightsListItemBinding>() {
    override fun bind(viewBinding: FlightsListItemBinding, position: Int) {
        viewBinding.flightsListItem = arrival
    }

    override fun getLayout(): Int = R.layout.flights_list_item
}