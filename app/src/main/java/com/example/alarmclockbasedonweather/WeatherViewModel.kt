package com.example.alarmclockbasedonweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel(){

    val snow = MutableLiveData<Boolean>()
    val rain = MutableLiveData<Boolean>()

    fun setRain(newWeatherCheckRain: Boolean) {
        rain.value = newWeatherCheckRain
    }
    fun setSnow(newWeatherCheckSnow: Boolean) {
        snow.value = newWeatherCheckSnow
    }

}