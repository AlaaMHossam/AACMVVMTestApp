package com.lifetimecode.aacmvvmtestapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lifetimecode.aacmvvmtestapp.R
import com.lifetimecode.aacmvvmtestapp.data.viewmodels.FlightsViewModel
import com.lifetimecode.aacmvvmtestapp.databinding.ActivityMainBinding
import com.lifetimecode.aacmvvmtestapp.ui.adapters.FlightsAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                //      message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //      message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                //      message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val flightsViewModel =
            ViewModelProviders.of(this, viewModelFactory)[FlightsViewModel::class.java]

        flightsViewModel.getFlights().observe(this, Observer {
            binding.flightData = it.result.arrivals[0]
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = FlightsAdapter(it.result.arrivals)
        })
    }
}
