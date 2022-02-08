package com.example.alarmclockbasedonweather

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Vibrator
import org.json.JSONObject
import java.net.URL
import java.time.LocalTime


const val CUSTOM_ALARM_SOUND = "customAlarmSound"
const val SELECTED_TIME_DELAY = "timeDelay"
const val USER_TEMP = "selectedTemp"
const val IS_TEMP_ALARM = "isTempAlarm"
const val IS_RAIN_ALARM = "isRainAlarm"
const val IS_SNOW_ALARM = "isSnowAlarm"
val ZIPCODE: String = "63021"
val COUNTRY: String ="US"
val API: String = "722ddb33c87d7eaca6217198f1ec38fe"
var hasStartedSecondAlarm = false
var hasStartedSecondWeatherAlarm = false

class AlarmBroadcastReceiver : BroadcastReceiver() {

    var customAlarmSound = 0
    var context: Context? = null
    var mHour: Int = 0
    var mMin: Int = 0
    var timeDelay: Int = 0
    var selectedTemp: Float = 0F
    var isRainAlarm = false
    var isSnowAlarm = false
    var temperatureAlarm = false


     inner class callAPIForTemp() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?zip=$ZIPCODE,$COUNTRY&appid=$API&units=imperial")
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

                val time = LocalTime.now()
                mHour = time.hour
                mMin = time.minute + timeDelay
                if(mMin >= 60) {
                    mHour + 1
                    mMin - 60
                }
                if (selectedTemp > tempFloat && !hasStartedSecondAlarm)  {
                    hasStartedSecondAlarm = true
                    createSecondAlarm()
                } else {
                    playAlarm()
                }
            } catch (e: java.lang.Exception) {
                println(e)
            }
        }
    }

    inner class callAPIForWeather() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?zip=$ZIPCODE,$COUNTRY&appid=$API")
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
                val weatherArray = jsonObj.getJSONArray("weather")
                val weatherObject = weatherArray.getJSONObject(0)
                val weatherType = weatherObject.getString("main")

                if (weatherType == "Rain" && isRainAlarm && !hasStartedSecondWeatherAlarm ){

                    val time = LocalTime.now()
                    mHour = time.hour
                    mMin = time.minute + timeDelay
                    if (mMin >= 60) {
                        mHour + 1
                        mMin - 60
                    }
                    hasStartedSecondWeatherAlarm = true
                    createSecondAlarm()
                } else if (weatherType == "Snow" && isSnowAlarm && !hasStartedSecondWeatherAlarm ){

                    val time = LocalTime.now()
                    mHour = time.hour
                    mMin = time.minute + timeDelay
                    if (mMin >= 60) {
                        mHour + 1
                        mMin - 60
                    }
                    hasStartedSecondWeatherAlarm = true
                    createSecondAlarm()
                } else {
                    playAlarm()
                }

            } catch (e: java.lang.Exception) {
                println(e)
            }
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        this.context = context

        customAlarmSound = intent.getIntExtra(CUSTOM_ALARM_SOUND, 0)
        timeDelay = intent.getIntExtra(SELECTED_TIME_DELAY, 0)
        selectedTemp = intent.getFloatExtra(USER_TEMP, 0F)
        temperatureAlarm =intent.getBooleanExtra(IS_TEMP_ALARM, false)
        isRainAlarm = intent.getBooleanExtra(IS_RAIN_ALARM, false)
        isSnowAlarm = intent.getBooleanExtra(IS_SNOW_ALARM, false)

        if (selectedTemp > 0){
            callAPIForTemp().execute()
        }else if(isRainAlarm || isSnowAlarm){
            callAPIForWeather().execute()
        }else{
            playAlarm()
        }

    }

    fun playAlarm(){
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(2000)
        val noti = Notification.Builder(context)
            .setContentTitle("Alarm is ON")
            .setContentText("You had set up the alarm")
            .setSmallIcon(R.mipmap.ic_launcher).build()
        val manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        noti.flags = noti.flags or Notification.FLAG_AUTO_CANCEL
        manager.notify(0, noti)
        val path: Uri = Uri.parse("android.resource://" + context?.packageName + "/" + customAlarmSound)
        val r = RingtoneManager.getRingtone(context, path)
        r.play()
    }

    fun createSecondAlarm(){
        val alarmManager = AlarmManager(context, mHour, mMin)
        alarmManager.setTimer(customAlarmSound)
    }
}


