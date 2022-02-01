package com.example.alarmclockbasedonweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.ButterKnife
import java.lang.Exception


class TempFragment : Fragment() {

    var currentTemp: Float = 0F
    var tempViewModel: TempViewModel? = null
    var timeDelay: Int = 0
    var delayViewModel: DelayViewModel? = null

    @BindView(R.id.tempEditText)
    lateinit var tempEditText: EditText
    @BindView(R.id.noneText)
    lateinit var minEditText: EditText
    @BindView(R.id.button2)
    lateinit var addConditionButton: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_temp, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tempViewModel = ViewModelProvider(requireActivity()).get(TempViewModel::class.java)

        addConditionButton.setOnClickListener() {

            try {
                currentTemp = tempEditText.text.toString().toFloat()
                tempViewModel?.setSelectedTemp(currentTemp)
                timeDelay = minEditText.text.toString().toInt()
                delayViewModel?.setTimeDelay(timeDelay)
            } catch (e: Exception) {
                Toast.makeText(context, " user input error", Toast.LENGTH_LONG).show()
            }
        }

        delayViewModel = ViewModelProvider(requireActivity()).get(DelayViewModel::class.java)
    }
}