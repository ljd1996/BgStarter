package com.hearing.bgstarter.starter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.hearing.bgstarter.utils.Utils

/**
 * @author liujiadong
 * @since 2021/1/28
 */
abstract class Starter {
    var next: Starter? = null

    abstract fun handle(context: Context, intent: Intent)

    abstract fun satisfy(): Boolean

    fun start(context: Context, intent: Intent, callback: StarterCallback? = null) {
        when {
            satisfy() -> {
                handle(context, intent)
                Utils.delayInMain(3000) {
                    val isStarted = callback?.judgeResult() ?: START_RESULT_UNKNOWN
                    Log.d(TAG, "[Starter] ${this.javaClass.simpleName} isStarted: $isStarted")
                    if (isStarted == START_RESULT_FAIL) {
                        if (next != null) {
                            next?.start(context, intent, callback)
                        } else {
                            callback?.result(isStarted)
                        }
                    } else {
                        callback?.result(isStarted)
                    }
                }
            }
            next != null -> next?.start(context, intent, callback)
            else -> callback?.result(START_RESULT_FAIL)
        }
    }

    companion object {
        const val TAG = "Starter"
        const val START_RESULT_SUCCESS = 0
        const val START_RESULT_FAIL = -1
        const val START_RESULT_UNKNOWN = -2

        fun startActivityBg(context: Context, intent: Intent, callback: StarterCallback? = null) {
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            val starterByApi = StarterByApi()
            val starterByTask = StarterByTask()
            val starterByHookMi = StarterByHookMi()
            val starterByNotification = StarterByNotification()
            starterByApi.next = starterByTask
            starterByTask.next = starterByHookMi
            starterByHookMi.next = starterByNotification
            starterByApi.start(context, intent, callback)
        }
    }
}