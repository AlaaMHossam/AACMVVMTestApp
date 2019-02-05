package com.lifetimecode.aacmvvmtestapp.ui.adapters

import android.animation.ObjectAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lifetimecode.aacmvvmtestapp.R
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.databinding.FlightsListItemBinding
import com.lifetimecode.aacmvvmtestapp.ui.adapters.FlightsAdapter.ViewHolder
import com.lifetimecode.aacmvvmtestapp.ui.fragments.HomeFragment
import kotlinx.android.synthetic.main.flights_list_item.view.*

class FlightsAdapter : RecyclerView.Adapter<ViewHolder>() {

    var itemPosition = 0

    private var arrivalsList: List<Arrival> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val flightsListItemBinding: FlightsListItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.flights_list_item, parent, false)

        return (ViewHolder(flightsListItemBinding))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(arrivalsList[position])

        holder.itemView.setOnClickListener {
            itemPosition = holder.adapterPosition
            Log.d("FlightsAdapter", "onBindViewHolder : $itemPosition")
        }
    }

    override fun getItemCount(): Int = arrivalsList.size

    fun updateAdapter(dataList: List<Arrival>) {
        arrivalsList = dataList
        notifyDataSetChanged()
    }

    /*fun updateList(newList: List<Arrival>) {
        val diffResult = DiffUtil.calculateDiff(Diff(newList, arrivalsList))
        arrivalsList.clear()
        arrivalsList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }*/

    inner class ViewHolder(private val flightsListItemBinding: FlightsListItemBinding) :
        RecyclerView.ViewHolder(flightsListItemBinding.root) {

        fun bind(arrival: Arrival) {
            flightsListItemBinding.flightsListItem = arrival
            flightsListItemBinding.clickHandler = HomeFragment()
            flightsListItemBinding.executePendingBindings()
        }
    }
}