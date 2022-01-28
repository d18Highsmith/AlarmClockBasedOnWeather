package com.example.alarmclockbasedonweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

import com.google.android.material.tabs.TabLayout


class AlarmFragment : Fragment() {

    var currentCustomSound: Int = R.raw.alarm1
    var currentTemp: Float = 0.00f


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
    var tempSelected = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_alarm, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(SoundViewModel::class.java)
        currentCustomSound = viewModel.currentSound.value ?: R.raw.alarm1
        viewModel.currentSound.observe(viewLifecycleOwner, Observer { newSound ->
            currentCustomSound = newSound
        })

        val viewModelTemp = ViewModelProvider(requireActivity()).get(TempViewModel::class.java)
        viewModelTemp.selectedTemp.observe(viewLifecycleOwner, Observer { newTemp ->
            currentTemp = newTemp
        })
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
                if (position == 1){
                    tempSelected = true
                }
                else{
                    tempSelected = false
                }
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

    //This is the value we want to share between the two fragments



    @OnClick(R.id.button)
     fun createAlarm(v: View) {
        if (tempSelected){
            val alarmManager = AlarmManager(context, mHour, mMin)
            alarmManager.setTimer(currentCustomSound, currentTemp)

        }
        else {
            val alarmManager = AlarmManager(context, mHour, mMin)
            alarmManager.setTimer(currentCustomSound)
        }
     }

}

