package com.lifetimecode.aacmvvmtestapp.ui.fragments

import android.content.Context
import android.content.res.Configuration.ORIENTATION_PORTRAIT
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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lifetimecode.aacmvvmtestapp.R
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.NoConnectivityException
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.data.viewmodels.FlightsViewModel
import com.lifetimecode.aacmvvmtestapp.databinding.FragmentHomeBinding
import com.lifetimecode.aacmvvmtestapp.ui.items.FlightsItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var flightsViewModel: FlightsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentHomeBinding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initViewModel()
        fetchData()
    }

    private fun initUI() {
        srl_home_flights.apply {
            isRefreshing = true
            setOnRefreshListener(this@HomeFragment)
        }

       rv_home_flights.let {
           if (activity?.resources?.configuration?.orientation == ORIENTATION_PORTRAIT)
               it.layoutManager = LinearLayoutManager(activity)
           else it.layoutManager = GridLayoutManager(activity, 2)
       }
 /*
            it.adapter = FlightsListAdapter()
        }*/

        rv_home_flights.adapter = GroupAdapter<ViewHolder>()

    }

    private fun initViewModel() {
        flightsViewModel = activity.run {
            ViewModelProviders.of(this!!, viewModelFactory)[FlightsViewModel::class.java]
        }

        flightsViewModel.flightsLiveData.observe(this, Observer {
            srl_home_flights.isRefreshing = false

            //  (rv_home_flights.adapter as FlightsListAdapter).submitList(it.result.arrivals)

            (rv_home_flights.adapter as GroupAdapter).addAll(it.result.arrivals.toArrivalItems())
        })
    }

    private fun List<Arrival>.toArrivalItems(): List<FlightsItem> {
        return this.map {
            FlightsItem(it)
        }
    }

    private val handler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is NoConnectivityException)
            CoroutineScope(Dispatchers.Main).launch {
                srl_home_flights.isRefreshing = false
                Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_LONG).show()
            }
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            flightsViewModel.getFlights(handler, true)
            Log.d("MainActivity", "onCreate : ${flightsViewModel.getArrivalDB()}")
        }
    }

    fun onFlightClicked(view: View, arrival: Arrival, position: Int) {

        Log.d("HomeFragment", "onFlightClicked : $position")

        val directions =
            HomeFragmentDirections.actionStartFlightDetails(arrival)
        Navigation.findNavController(view).navigate(directions)
    }

    override fun onRefresh() {
        fetchData()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
