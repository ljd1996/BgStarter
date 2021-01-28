package com.hearing.bgstarter.starter

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.hearing.bgstarter.R

/**
 * @author liujiadong
 * @since 2021/1/28
 */
class StarterByNotification : Starter() {
    private var manager: NotificationManager? = null

    override fun handle(context: Context, intent: Intent) {
        sendNotificationFullScreen(context, "Title", "Content", intent)
    }

    override fun satisfy(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    private fun getNotificationManagerManager(context: Context): NotificationManager? {
        if (manager == null) {
            manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        }
        return manager
    }

    private fun sendNotificationFullScreen(
        context: Context,
        title: String?,
        content: String?,
        intent: Intent
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            clearNotification(context) // 没清除的话有时候发现弹不出来
            val channel = NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_HIGH)
            channel.setSound(null, null)
            getNotificationManagerManager(context)?.createNotificationChannel(channel)
            val notification = getChannelNotificationQ(context, title, content, intent)
            getNotificationManagerManager(context)?.notify(NOTIFICATION_ID, notification)
        }
    }

    private fun clearNotification(context: Context) {
        getNotificationManagerManager(context)?.cancel(NOTIFICATION_ID)
    }

    private fun getChannelNotificationQ(
        context: Context,
        title: String?,
        content: String?,
        intent: Intent
    ): Notification {
        val fullScreenPendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notificationBuilder = NotificationCompat.Builder(context, ID)
            .setSmallIcon(R.drawable.white)
            .setContentTitle(title)
            .setContentText(content)
            .setSound(null)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(Notification.CATEGORY_CALL)
            .setFullScreenIntent(fullScreenPendingIntent, true)
        return notificationBuilder.build()
    }

    companion object {
        private const val ID = "channel_out"
        private const val NAME = "notification_out"
        private const val NOTIFICATION_ID: Int = 11112
    }
}