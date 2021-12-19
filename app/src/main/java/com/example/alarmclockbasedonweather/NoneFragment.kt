package com.example.alarmclockbasedonweather

import android.app.AlarmManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife

class NoneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_none, container, false)
        ButterKnife.bind(this@NoneFragment, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        startNewFunction()
    }

    private fun startNewFunction() {
//        val intent = Intent(this@NoneFragment, AlarmManager::class.java)
//            startActivity(intent)
//
//
//     }
    }
}


