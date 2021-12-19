package com.example.alarmclockbasedonweather

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Vibrator


class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(2000)
        val noti = Notification.Builder(context)
            .setContentTitle("Alarm is ON")
            .setContentText("You had set up the alarm")
            .setSmallIcon(R.mipmap.ic_launcher).build()
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        noti.flags = noti.flags or Notification.FLAG_AUTO_CANCEL
        manager.notify(0, noti)
        val notifcation = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        val r = RingtoneManager.getRingtone(context, notifcation)
        r.play()
    }
}
