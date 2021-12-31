package com.example.alarmclockbasedonweather

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Vibrator


const val CUSTOM_ALARM_SOUND = "customAlarmSound"

class AlarmBroadcastReceiver : BroadcastReceiver() {

    var customAlarmSound = 0

    override fun onReceive(context: Context, intent: Intent) {
        customAlarmSound = intent.getIntExtra(CUSTOM_ALARM_SOUND, 0)
//call api get temp, take temp caompare to user temp criteria, if temp fits send, else set new alarm with delay
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(2000)
        val noti = Notification.Builder(context)
            .setContentTitle("Alarm is ON")
            .setContentText("You had set up the alarm")
            .setSmallIcon(R.mipmap.ic_launcher).build()
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        noti.flags = noti.flags or Notification.FLAG_AUTO_CANCEL
        manager.notify(0, noti)

        val path: Uri = Uri.parse("android.resource://" + context.packageName + "/" + customAlarmSound)
        val r = RingtoneManager.getRingtone(context, path)
        r.play()

      /*  val notifcation = RingtoneManager.getDefaultUri(customAlarmSound)
        val r = RingtoneManager.getRingtone(context, notifcation)
        r.play()*/
    }
}
