package com.example.alarmclockbasedonweather

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import org.json.JSONObject
import java.net.URL
import java.time.LocalDateTime


const val CUSTOM_ALARM_SOUND = "customAlarmSound"
val CITY: String = "dhaka,bd"
val API: String = "722ddb33c87d7eaca6217198f1ec38fe"

class AlarmBroadcastReceiver : BroadcastReceiver() {

    var customAlarmSound = 0
    var context: Context? = null

     inner class callAPI() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API")
                        .readText(Charsets.UTF_8)
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val tempFloat = main.getString("temp").toFloat()
                println(tempFloat)

                val alarmManager = AlarmManager(context, 14, 11)
                alarmManager.setTimer(customAlarmSound)
            } catch (e: java.lang.Exception) {
                println(e)
            }
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        this.context = context
        callAPI().execute()
//        customAlarmSound = intent.getIntExtra(CUSTOM_ALARM_SOUND, 0)
////call api get temp, take temp caompare to user temp criteria, if temp fits send, else set new alarm with delay
//        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//        vibrator.vibrate(2000)
//        val noti = Notification.Builder(context)
//            .setContentTitle("Alarm is ON")
//            .setContentText("You had set up the alarm")
//            .setSmallIcon(R.mipmap.ic_launcher).build()
//        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        noti.flags = noti.flags or Notification.FLAG_AUTO_CANCEL
//        manager.notify(0, noti)
//        val path: Uri = Uri.parse("android.resource://" + context.packageName + "/" + customAlarmSound)
//        val r = RingtoneManager.getRingtone(context, path)
//        r.play()
    }
}
