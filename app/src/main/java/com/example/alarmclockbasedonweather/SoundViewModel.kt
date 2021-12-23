package com.example.alarmclockbasedonweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SoundViewModel: ViewModel() {

    val currentSound = MutableLiveData<Int>()

    fun setSound(newSound: Int) {
        currentSound.value = newSound
    }

}