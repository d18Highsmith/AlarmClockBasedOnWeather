package com.example.alarmclockbasedonweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TempViewModel : ViewModel(){

    val selectedTemp = MutableLiveData<Float>()
    val currentTemp = MutableLiveData<Float>()

    fun setSelectedTemp(newTemp: Float) {
        selectedTemp.value = newTemp
    }

    fun setCurrentTemp(newTemp: Float) {
        currentTemp.value = newTemp
    }

}