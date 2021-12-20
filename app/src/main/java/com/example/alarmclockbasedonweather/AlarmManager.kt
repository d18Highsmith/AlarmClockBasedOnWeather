package com.example.alarmclockbasedonweather

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import java.util.*

class AlarmManager(var context: Context?, var mHour: Int, var mMin: Int) {


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        timePicker = findViewById<View>(R.id.timePicker) as TimePicker
//        textView = findViewById<View>(R.id.timeTextView) as TextView
//        timePicker!!.setOnTimeChangedListener { view, hourOfDay, minute ->
//            mHour = hourOfDay
//            mMin = minute
//            textView!!.text = textView!!.text.toString() + " " + mHour + ":" + mMin
//        }
//    }

    fun setTimer() {
        val alarmManager = context?.getSystemService(ALARM_SERVICE) as AlarmManager?
        val date = Date()
        val cal_alarm = Calendar.getInstance()
        val cal_now = Calendar.getInstance()
        cal_now.time = date
        cal_alarm.time = date
        cal_alarm[Calendar.HOUR_OF_DAY] = mHour
        cal_alarm[Calendar.MINUTE] = mMin
        cal_alarm[Calendar.SECOND] = 0
        if (cal_alarm.before(cal_now)) {
            cal_alarm.add(Calendar.DATE, 1)
        }
        val i = Intent(context, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 24444, i, 0)
        alarmManager?.set(AlarmManager.RTC_WAKEUP, cal_alarm.timeInMillis, pendingIntent)
    }
}