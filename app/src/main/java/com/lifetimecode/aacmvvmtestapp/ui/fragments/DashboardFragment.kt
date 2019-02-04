package com.lifetimecode.aacmvvmtestapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.lifetimecode.aacmvvmtestapp.R
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {

    val list = listOf("test1", "test2")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewPager.adapter = MyPagerAdapter()
        dots_indicator.setViewPager(viewPager)
    }

    inner class MyPagerAdapter : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(activity)
            val layout = inflater.inflate(R.layout.activity_flight_details, container, false)
            container.addView(layout)
            return layout
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return true
        }

        override fun getCount(): Int = list.size
    }
}
