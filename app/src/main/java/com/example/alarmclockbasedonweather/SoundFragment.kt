package com.example.alarmclockbasedonweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_sound.view.*

class SoundFragment : Fragment(), RecyclerAdapter.CustomSoundInterface {

    var viewModel: SoundViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sound, container, false)

        var alarm1 = Alarm("alarm1", R.drawable.ic_blue, R.raw.alarm1)
        var alarm2 = Alarm("alarm2", R.drawable.ic_green, R.raw.alarm2)
        var alarm3 = Alarm("alarm3", R.drawable.ic_yellow, R.raw.alarm3)

        var myList = ArrayList<Alarm>()

            myList.add(alarm1)
            myList.add(alarm2)
            myList.add(alarm3)

        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        context?.let {
            view.recyclerView.adapter = RecyclerAdapter(myList, it, this)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SoundViewModel::class.java)
    }

    //This is the value we want to share between the two fragments
   // var currentCustomSound: Int = 0

    override fun setCustomSound(customSound: Int) {
        viewModel?.setSound(customSound)
       // currentCustomSound = customSound
    }

}
