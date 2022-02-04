package com.example.alarmclockbasedonweather

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import java.util.*

class AlarmManager(var context: Context?, var mHour: Int, var mMin: Int) {



    fun setTimer(customAlarmSound: Int, selectedTemp: Float, selectedDelay: Int) {
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

        //will have to put a time delay somewhere here
        val i = Intent(context, AlarmBroadcastReceiver::class.java)
        i.putExtra(CUSTOM_ALARM_SOUND, customAlarmSound)
        i.putExtra(SELECTED_TIME_DELAY, selectedDelay)
        i.putExtra(USER_TEMP, selectedTemp)
        val pendingIntent = PendingIntent.getBroadcast(context, 24444, i, 0)
        alarmManager?.set(AlarmManager.RTC_WAKEUP, cal_alarm.timeInMillis, pendingIntent)
    }

    fun setTimer(customAlarmSound: Int) {
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
        i.putExtra(CUSTOM_ALARM_SOUND, customAlarmSound)
        val pendingIntent = PendingIntent.getBroadcast(context, 24444, i, 0)
        alarmManager?.set(AlarmManager.RTC_WAKEUP, cal_alarm.timeInMillis, pendingIntent)
    }

    fun setTimer(customAlarmSound: Int, selectedDelay: Int, isRainChecked: Boolean, isSnowChecked: Boolean) {
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
        i.putExtra(CUSTOM_ALARM_SOUND, customAlarmSound)
        i.putExtra(IS_RAIN_ALARM, isRainChecked)
        i.putExtra(IS_SNOW_ALARM, isSnowChecked)
        i.putExtra(SELECTED_TIME_DELAY, selectedDelay)
        val pendingIntent = PendingIntent.getBroadcast(context, 24444, i, 0)
        alarmManager?.set(AlarmManager.RTC_WAKEUP, cal_alarm.timeInMillis, pendingIntent)
    }
}