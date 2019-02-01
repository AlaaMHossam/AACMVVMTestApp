package com.lifetimecode.aacmvvmtestapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lifetimecode.aacmvvmtestapp.R
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.databinding.FlightsListItemBinding
import com.lifetimecode.aacmvvmtestapp.ui.adapters.FlightsAdapter.ViewHolder
import com.lifetimecode.aacmvvmtestapp.ui.fragments.HomeFragment

class FlightsAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var arrivalsList: List<Arrival> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val flightsListItemBinding: FlightsListItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.flights_list_item, parent, false)

        return (ViewHolder(flightsListItemBinding))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(arrivalsList[position])

    override fun getItemCount(): Int = arrivalsList.size

    fun updateAdapter(dataList: List<Arrival>){
        arrivalsList = dataList
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(private val flightsListItemBinding: FlightsListItemBinding) :
        RecyclerView.ViewHolder(flightsListItemBinding.root) {

        fun bind(arrival: Arrival) {
            flightsListItemBinding.flightsListItem = arrival
            flightsListItemBinding.clickHandler = HomeFragment()
            flightsListItemBinding.executePendingBindings()
        }
    }
}