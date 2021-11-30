package com.example.alarmclockbasedonweather

import android.content.Context
import android.media.MediaPlayer
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val myList: ArrayList<Alarm>, var context: Context): RecyclerView.Adapter<CustomViewHolder>() {


    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.card_layout, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.textAlignment
        var currentAlarm = myList.get(position)
        holder.textView.text = currentAlarm.name
        holder.imageView.setImageResource(currentAlarm.image)

        holder.view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                var myMediaPlayer = MediaPlayer.create(context, currentAlarm.sound)
                myMediaPlayer.start()
            }
        })
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val imageView: ImageView = itemView.findViewById(R.id.item_image)
    val textView: TextView = itemView.findViewById(R.id.item_title)

}



