package com.ljuns.kotlintest.data

import android.util.Log
import java.net.URL

/**
 * @author ljuns
 * Created at 2018/11/28.
 */
class Request(private val url: String) {
    fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}