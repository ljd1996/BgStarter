package com.hearing.bgstarter.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hearing.bgstarter.R
import com.hearing.bgstarter.starter.Starter
import com.hearing.bgstarter.starter.StarterCallback
import com.hearing.bgstarter.utils.Utils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click(view: View) {
        moveTaskToBack(true)
        Utils.delayInMain(2000) {
            Starter.startActivityBg(this,
                Intent(this, TestActivity::class.java),
                object : StarterCallback() {
                    override fun judgeResult(): Int {
                        return if (TestActivity.started) {
                            Starter.START_RESULT_SUCCESS
                        } else {
                            Starter.START_RESULT_FAIL
                        }
                    }

                    override fun result(result: Int) {
                        Log.d(Starter.TAG, "result: $result")
                    }
                })
        }
    }
}