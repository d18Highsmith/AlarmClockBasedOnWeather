package com.example.alarmclockbasedonweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2

import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_alarm.*

class AlarmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_alarm, container, false)
        setUpTabBar()
    }

        private fun setUpTabBar()
        {
            val adapter = TabPageAdapter2(this, tabSecondLayout.tabCount)
            viewPagerSmall.adapter = adapter

            viewPagerSmall.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback()
            {
                override fun onPageSelected(position: Int) {
                    tabSecondLayout.selectTab(tabSecondLayout.getTabAt(position))
                }
            })

            tabSecondLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {}
                override fun onTabReselected(p0: TabLayout.Tab?) {}

            })



        }
    }

