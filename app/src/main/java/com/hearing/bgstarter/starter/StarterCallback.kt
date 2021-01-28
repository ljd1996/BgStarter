package com.hearing.bgstarter.starter

/**
 * @author liujiadong
 * @since 2021/1/28
 */
open class StarterCallback {
    // 最终结果
    open fun result(result: Int) {}

    // 判断 Activity 是否启动成功
    open fun judgeResult(): Int = Starter.START_RESULT_UNKNOWN
}