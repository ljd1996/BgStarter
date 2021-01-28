package com.hearing.bgstarter.starter

import android.app.ActivityManager
import android.content.Context
import android.content.Intent

/**
 * @author liujiadong
 * @since 2021/1/28
 */
class StarterByTask : Starter() {
    override fun handle(context: Context, intent: Intent) {
        moveToFront(context)
        context.startActivity(intent)
    }

    override fun satisfy(): Boolean = true

    private fun moveToFront(context: Context) {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
        activityManager?.getRunningTasks(50)?.forEach { taskInfo ->
            if (taskInfo.topActivity?.packageName == context.packageName) {
                activityManager.moveTaskToFront(taskInfo.id, 0)
                activityManager.moveTaskToFront(taskInfo.id, 0)
                return
            }
        }
    }
}