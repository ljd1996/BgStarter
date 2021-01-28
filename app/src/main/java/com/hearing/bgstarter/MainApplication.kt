package com.hearing.bgstarter

import android.app.Application
import android.content.Context
import com.hearing.bgstarter.starter.StarterByHookMi

/**
 * @author liujiadong
 * @since 2021/1/28
 */
class MainApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        StarterByHookMi.fix(base ?: this)
    }
}