package com.example.alarmclockbasedonweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_sound.*
import kotlinx.android.synthetic.main.fragment_sound.view.*

class SoundFragment : Fragment() {

//    private var layoutManager: RecyclerView.LayoutManager? = null
//    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

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
            view.recyclerView.adapter = RecyclerAdapter(myList, it)
        }


        return view
    }}
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)chaptersList.add(“Android MVP Introduction”)
//        chaptersList.add(“Learn RxJava”)
//        chaptersList.add(“Advance Kotlin”)layoutManager = LinearLayoutManager(this)
//        rvChapterList.layoutManager = layoutManager
//        rvChapterList.adapter = ChapterAdapter(this, chaptersList)
//    }
//}



//       layoutManager = LinearLayoutManager(this.context)
//        recyclerView.layoutManager = layoutManager
//        adapter = RecyclerAdapter()
//        recyclerView.adapter = adapter


//        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
//
//        recyclerview.layoutManager = LinearLayoutManager(this)
//
//        val data = ArrayList<ItemsViewModel>()
//
//        val adapter = RecyclerAdapter(data)
//
//        recyclerview.adapter = adapter
//
//        return inflater.inflate(R.layout.fragment_sound, container, false)

