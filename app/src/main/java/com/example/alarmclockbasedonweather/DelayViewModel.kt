package com.example.alarmclockbasedonweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DelayViewModel : ViewModel(){

    val timeDelay = MutableLiveData<Int>()

    fun setTimeDelay(newTimeDelay: Int) {
        timeDelay.value = newTimeDelay
    }

}