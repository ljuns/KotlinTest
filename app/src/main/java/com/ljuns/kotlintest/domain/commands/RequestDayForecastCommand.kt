package com.ljuns.kotlintest.domain.commands

import com.ljuns.kotlintest.domain.datasource.ForecastProvider
import com.ljuns.kotlintest.domain.model.Forecast

/**
 * Created by ljuns at 2018/12/5.
 * I am just a developer.
 * 介于 Activity 与请求层，请求某一天的天气
 */

class RequestDayForecastCommand(private val id: Long,
                                private val forecastProvider: ForecastProvider = ForecastProvider())
    : Command<Forecast> {

    override fun execute(): Forecast = forecastProvider.requestDayForecast(id)

}