package com.example.alarmclockbasedonweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife


class TempFragment : Fragment() {

    var currentTemp: Int = 0

    @BindView(R.id.textView)
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

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        Toast.makeText(context, "toast ${temp}", Toast.LENGTH_LONG).show()



//    }
}