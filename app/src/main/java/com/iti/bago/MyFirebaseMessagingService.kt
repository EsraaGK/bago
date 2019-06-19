package com.iti.bago


import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Vibrator
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.iti.bago.tabbarActivity.TabbarActivity
import com.iti.bago.tabbarActivity.home.store.HomeFragment
import java.util.*


class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "MyFirebaseToken"

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.i(TAG, token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

      //  sendNotification(remoteMessage!!)

        notify(remoteMessage)

    }


    private fun sendNotification(remoteMessage: RemoteMessage) {

        val intent = Intent(this, TabbarActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
            .setContentTitle(remoteMessage.notification!!.title)
            .setContentText(remoteMessage.notification!!.body)
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.app_btn)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())

    }

    private fun notify(remoteMessage: RemoteMessage?) {

//        val fcmToken: FCMResponse = FCMResponse(
//            remoteMessage!!.data["id"] as String,
//            remoteMessage.data["tans_Id"] as String,
//            remoteMessage.data["image"] as String,
//            remoteMessage.data["long"] as String,
//            remoteMessage.data["lat"] as String,
//            remoteMessage.data["name"] as String
//        )

        val mNotifyManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, TabbarActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
     //   intent.putExtra("Notification", "patient")

        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT)
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(1000)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChannel(mNotifyManager)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val mBuilder = NotificationCompat.Builder(this, "id")
            .setTicker("Bago Notification")
            .setContentTitle("Order Tracking")
            .setContentText(remoteMessage!!.notification!!.body)
            .setSmallIcon(R.mipmap.app_btn)
            //.setColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
        mNotifyManager.notify(1, mBuilder.build())
    }

    @TargetApi(26)
    private fun createChannel(notificationManager: NotificationManager) {

        val name = "id"
        val description = "Notifications for download status"
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val mChannel = NotificationChannel(name, name, importance)
        mChannel.description = description
        mChannel.enableLights(true)
        mChannel.lightColor = Color.BLUE
        notificationManager.createNotificationChannel(mChannel)
    }
}

