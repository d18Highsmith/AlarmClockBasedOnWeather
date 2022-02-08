package com.example.alarmclockbasedonweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_percipitation.*
import java.lang.Exception

class PercipitationFragment : Fragment() {

    var timeDelay: Int = 0
    var delayViewModel: DelayViewModel? = null
    var weatherViewModel: WeatherViewModel? = null

    @BindView(R.id.button)
    lateinit var addConditionButton: Button
    @BindView(R.id.switchSnow)
    lateinit var snowSwitch: Switch
    @BindView(R.id.switchRain)
    lateinit var rainSwitch: Switch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_percipitation, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherViewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)
        delayViewModel = ViewModelProvider(requireActivity()).get(DelayViewModel::class.java)

        addConditionButton.setOnClickListener() {

            try {
                timeDelay = lengthText.text.toString().toInt()
                delayViewModel?.setTimeDelay(timeDelay)
                Toast.makeText(context, "condition set", Toast.LENGTH_SHORT).show()
                weatherViewModel?.setRain(rainSwitch.isChecked)
                weatherViewModel?.setSnow(snowSwitch.isChecked)
            } catch (e: Exception) {
                Toast.makeText(context, " user input error", Toast.LENGTH_LONG).show()
            }
        }
    }
}