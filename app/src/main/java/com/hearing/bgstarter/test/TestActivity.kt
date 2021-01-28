package com.hearing.bgstarter.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author liujiadong
 * @since 2021/1/28
 */
class TestActivity : AppCompatActivity() {
    companion object {
        var started = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        started = true
    }
}