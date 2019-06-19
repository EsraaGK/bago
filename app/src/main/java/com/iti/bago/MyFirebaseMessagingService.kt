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
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.iti.bago.tabbarActivity.TabbarActivity
import com.iti.bago.tabbarActivity.profile.orders.DetailsResponseObj
import com.iti.bago.tabbarActivity.profile.orders.OrderDetailsFragment
import com.iti.bago.tabbarActivity.profile.orders.ProductItem


class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "MyFirebaseToken"

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.i(TAG, token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)


        val json :String
        var responseJson : String
        var body:String

        if (remoteMessage!!.data["body"] != null) {

            Log.i("PVL", "RECEIVED MESSAGE: " + remoteMessage.data["body"] )

            json = remoteMessage.data["body"]  as String
            body = json.split("$")[0]
            responseJson = json.split("$")[1]
            Log.i("PVL", responseJson)
            notify(remoteMessage,responseJson,body)
        } else {
            Log.i("PVL", "RECEIVED MESSAGE: failed" )
        }
    }

    private fun notify(remoteMessage: RemoteMessage?,responseJson:String,body:String) {

        val mNotifyManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, TabbarActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("details_object", responseJson)

        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT)
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(1000)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChannel(mNotifyManager)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val mBuilder = NotificationCompat.Builder(this, "id")
            .setTicker("Bago Notification")
            .setContentTitle(remoteMessage!!.data["title"])
            .setContentText(body)
            .setSmallIcon(R.mipmap.app_btn)
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