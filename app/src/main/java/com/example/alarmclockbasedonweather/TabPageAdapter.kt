package com.example.alarmclockbasedonweather

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPageAdapter(activity: MainActivity, private val tabCount: Int) : FragmentStateAdapter(activity)
{
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment
    {
        return when(position)
        {
            0->{HomeFragment()}
            1->{SoundFragment()}
            2->{AlarmFragment()}
            else->HomeFragment()
        }
}

}