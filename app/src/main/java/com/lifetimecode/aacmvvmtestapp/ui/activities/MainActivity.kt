package com.lifetimecode.aacmvvmtestapp.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lifetimecode.aacmvvmtestapp.R
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.NoConnectivityException
import com.lifetimecode.aacmvvmtestapp.data.viewmodels.FlightsViewModel
import com.lifetimecode.aacmvvmtestapp.databinding.ActivityMainBinding
import com.lifetimecode.aacmvvmtestapp.ui.adapters.FlightsAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var flightsViewModel: FlightsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        flightsViewModel =
            ViewModelProviders.of(this, viewModelFactory)[FlightsViewModel::class.java]

        flightsViewModel.flightsLiveData.observe(this, Observer {
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = FlightsAdapter(it.result.arrivals)
        })

        CoroutineScope(Dispatchers.IO).launch(handler) {
            flightsViewModel.getFlightsUpdateDB(handler)
            Log.d("MainActivity", "onCreate : ${flightsViewModel.getFlightsDB()}")
        }

        val navController = findNavController(R.id.nav_host_fragment)
        navigation.setupWithNavController(navController)
    }

    private val handler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is NoConnectivityException)
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(this@MainActivity, "No Internet Connection", Toast.LENGTH_LONG).show()
            }
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

}
