package com.example.alarmclockbasedonweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TempViewModel : ViewModel(){

    val currentTemp = MutableLiveData<Float>()
    fun setTemp(newTemp: Float) {
        currentTemp.value = newTemp
    }

}