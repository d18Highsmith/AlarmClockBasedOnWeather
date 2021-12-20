package com.example.alarmclockbasedonweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.viewpager2.widget.ViewPager2
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

import com.google.android.material.tabs.TabLayout


class AlarmFragment : Fragment() {

    @BindView(R.id.tabSecondLayout)
    lateinit var tabSecondLayout: TabLayout
    @BindView(R.id.viewPagerSmall)
    lateinit var viewPagerSmall: ViewPager2
    @BindView(R.id.button)
    lateinit var myButton: Button
    @BindView(R.id.timePicker)
    lateinit var myTimePicker: TimePicker
    var mHour = 0
    var mMin = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_alarm, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        setUpTabBar()
        myTimePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            mHour = hourOfDay
            mMin = minute
        }
    }

    private fun setUpTabBar() {
        val adapter = TabPageAdapter2(this, tabSecondLayout.tabCount)
        viewPagerSmall.adapter = adapter

        viewPagerSmall.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabSecondLayout.selectTab(tabSecondLayout.getTabAt(position))
            }
        })

        tabSecondLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPagerSmall.currentItem = tab.position
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabReselected(p0: TabLayout.Tab?) {}
        })
    }


    @OnClick(R.id.button)
     fun createAlarm(v: View) {
         val alarmManager = AlarmManager(context, mHour, mMin)
         alarmManager.setTimer()
     }

}

