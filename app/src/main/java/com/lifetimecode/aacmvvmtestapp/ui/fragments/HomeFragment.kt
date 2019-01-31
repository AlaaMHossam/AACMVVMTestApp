package com.lifetimecode.aacmvvmtestapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lifetimecode.aacmvvmtestapp.R
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.NoConnectivityException
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.data.viewmodels.FlightsViewModel
import com.lifetimecode.aacmvvmtestapp.databinding.FragmentHomeBinding
import com.lifetimecode.aacmvvmtestapp.ui.adapters.FlightsAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var flightsViewModel: FlightsViewModel

    private val arrivalsList: MutableList<Arrival> = mutableListOf()

    private val adapter: FlightsAdapter = FlightsAdapter(arrivalsList)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentHomeBinding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        initStuff()

        return fragmentHomeBinding.root
    }


    private fun initStuff() {

        flightsViewModel = activity.run {
            ViewModelProviders.of(this!!, viewModelFactory)[FlightsViewModel::class.java]
        }

        CoroutineScope(Dispatchers.IO).launch(handler) {
            flightsViewModel.getFlights(handler)
            Log.d("MainActivity", "onCreate : ${flightsViewModel.getFlightsDB()}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_home_flights.let {
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = adapter
        }

        flightsViewModel.flightsLiveData.observe(this, Observer {
            arrivalsList.clear()
            arrivalsList.addAll(it.result.arrivals)
            adapter.notifyDataSetChanged()
        })
    }

    private val handler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is NoConnectivityException)
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_LONG).show()
            }
    }

    fun onFlightClicked(arrival: Arrival) {
    //    Log.d("HomeFragment", "onSaveClick : $view")
         Log.d("HomeFragment", "onSaveClick : ${arrival.airlineName}")
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
