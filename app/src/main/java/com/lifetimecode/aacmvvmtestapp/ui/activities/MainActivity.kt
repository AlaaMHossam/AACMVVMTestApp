package com.lifetimecode.aacmvvmtestapp.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lifetimecode.aacmvvmtestapp.R
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.NoConnectivityException
import com.lifetimecode.aacmvvmtestapp.data.viewmodels.FlightsViewModel
import com.lifetimecode.aacmvvmtestapp.databinding.ActivityMainBinding
import com.lifetimecode.aacmvvmtestapp.ui.adapters.FlightsAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var flightsViewModel: FlightsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        flightsViewModel =
            ViewModelProviders.of(this, viewModelFactory)[FlightsViewModel::class.java]

        flightsViewModel.flightsLiveData.observe(this, Observer {
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = FlightsAdapter(it.result.arrivals)
        })



       // flightsViewModel.getFlightsUpdateDB()

        CoroutineScope(Dispatchers.IO).launch(handler) {
            flightsViewModel.getFlightsUpdateDB(handler)
            Log.d("MainActivity", "onCreate : ${flightsViewModel.getFlightsDB()}")
        }
    }

    private val handler = CoroutineExceptionHandler { _, throwable ->
      if (throwable is NoConnectivityException)
          Log.d("MainActivity", " : ")
        GlobalScope.launch(Dispatchers.Main) {

            Toast.makeText(this@MainActivity, "No Internet Connection", Toast.LENGTH_LONG).show()
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
