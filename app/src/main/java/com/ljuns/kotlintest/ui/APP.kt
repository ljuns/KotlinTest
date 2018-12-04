package com.ljuns.kotlintest.ui

import android.app.Application
import com.ljuns.kotlintest.extensions.NotNullSingleValueVar

/**
 * Created by ljuns at 2018/12/2.
 * I am just a developer.
 */

class APP : Application() {

    companion object {
        // 调用 instance 对应的 get()、set() 方法时会调用 NotNullSingleValueVar 类中对应的 getValue()、setValue()
        var instance: Application by NotNullSingleValueVar()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}