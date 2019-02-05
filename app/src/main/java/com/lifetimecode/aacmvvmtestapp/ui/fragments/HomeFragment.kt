package com.lifetimecode.aacmvvmtestapp.ui.fragments

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lifetimecode.aacmvvmtestapp.R
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.NoConnectivityException
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.data.viewmodels.FlightsViewModel
import com.lifetimecode.aacmvvmtestapp.databinding.FragmentHomeBinding
import com.lifetimecode.aacmvvmtestapp.ui.adapters.FlightsAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.flights_list_item.view.*
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

            it.adapter = FlightsAdapter()
        }
    }

    private fun initViewModel() {
        flightsViewModel = activity.run {
            ViewModelProviders.of(this!!, viewModelFactory)[FlightsViewModel::class.java]
        }

        flightsViewModel.flightsLiveData.observe(this, Observer {
            srl_home_flights.isRefreshing = false
            // (rv_home_flights.adapter as FlightsAdapter).updateList(it.result.arrivals)
            (rv_home_flights.adapter as FlightsAdapter).updateAdapter(it.result.arrivals)
        })
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

    fun onFlightClicked(view: View, arrival: Arrival) {

        //   rv_home_flights.adapter?.notifyItemChanged(view.verticalScrollbarPosition)
        if (view.tag == "expanded")
            animationCollapse(view)
        else animationExpand(view)

        /*val directions =
            HomeFragmentDirections.actionStartFlightDetails(arrival)
        Navigation.findNavController(view).navigate(directions)*/
    }

    private fun animationExpand(view: View) {

        view.tag = "expanded"

        ObjectAnimator.ofFloat(view.cl_flight_list_details_holder, View.TRANSLATION_Y, 1f, 10f).setDuration(300).start()
        rv_home_flights.adapter?.notifyItemChanged((rv_home_flights.adapter as FlightsAdapter).itemPosition)

       /* view.cl_flight_list_details_holder
            .animate()
            .translationY(10f)
            .setDuration(300)
            .setInterpolator(OvershootInterpolator(1.5f))
            .start()*/

        view.tv_flight_list_details.visibility = View.VISIBLE
    }

    private fun animationCollapse(view: View) {

        view.tag = ""

        view.cl_flight_list_details_holder
            .animate()
            .translationY(-10f)
            .setDuration(300)
            .setInterpolator(OvershootInterpolator(1.5f))
            .start()

        view.tv_flight_list_details.visibility = View.GONE

    }

    override fun onRefresh() {
        fetchData()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
