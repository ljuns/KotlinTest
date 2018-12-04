package com.ljuns.kotlintest.domain.model

/**
 * Created by ljuns at 2018/11/29.
 * I am just a developer.
 * 实际需要的类型
 */

data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast: List<Forecast>) {

    /**
     * 操作符重载 operator fun
     */
    operator fun get(position: Int): Forecast = dailyForecast[position]

    fun size() = dailyForecast.size
}

data class Forecast(val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)