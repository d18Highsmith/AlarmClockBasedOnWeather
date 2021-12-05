package com.example.alarmclockbasedonweather;

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPageAdapter2(activity: AlarmFragment, private val tabCount: Int) : FragmentStateAdapter(activity)
{
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int):Fragment
    {
        return when(position)
        {
            0 -> { NoneFragment() }
            1 -> { TempFragment() }
            2 -> { PercipitationFragment() }
            else -> NoneFragment()
        }
    }
}

