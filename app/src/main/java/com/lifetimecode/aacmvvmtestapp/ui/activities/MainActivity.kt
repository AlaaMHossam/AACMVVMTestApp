package com.lifetimecode.aacmvvmtestapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lifetimecode.aacmvvmtestapp.R
import com.lifetimecode.aacmvvmtestapp.data.viewmodels.FlightsViewModel
import com.lifetimecode.aacmvvmtestapp.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
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

        /* flightsViewModel.getFlightsNetworkUpdateDB().observe(this, Observer {
             binding.flightData = it.result.arrivals[0]
             rv.layoutManager = LinearLayoutManager(this)
             rv.adapter = FlightsAdapter(it.result.arrivals)


             Log.d("MainActivity", "onCreate : ${flightsViewModel.getArrivalDB()}")
         })*/

        flightsViewModel.start()
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
