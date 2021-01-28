package com.hearing.bgstarter.starter

import android.content.Context
import android.content.Intent

/**
 * @author liujiadong
 * @since 2020/12/22
 */
class StarterByApi : Starter() {
    override fun handle(context: Context, intent: Intent) {
        context.startActivity(intent)
    }

    override fun satisfy(): Boolean = true
}