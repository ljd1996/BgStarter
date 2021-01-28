package com.hearing.bgstarter.utils

import android.os.Handler
import android.os.Looper

/**
 * @author liujiadong
 * @since 2021/1/28
 */
object Utils {
    fun delayInMain(delay: Long, cb: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({ cb.invoke() }, delay)
    }
}