package com.ljuns.kotlintest.domain.model

/**
 * Created by ljuns at 2018/11/29.
 * I am just a developer.
 * 实际需要的类型
 */

data class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Int, val low: Int)