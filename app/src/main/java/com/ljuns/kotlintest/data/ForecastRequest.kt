package com.ljuns.kotlintest.data

import com.google.gson.Gson
import java.net.URL

/**
 * Created by ljuns at 2018/11/29.
 * I am just a developer.
 */

class ForecastRequest(val zipCode: String) {

    /**
     * 此处使用 companion object {} 达到静态代码块的作用
     */
    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    /**
     * 真正的网络请求
     */
    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        // ForecastResult::class.java 相当于 Java 中的 ForecastResult.class
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}