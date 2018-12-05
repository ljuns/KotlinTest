package com.ljuns.kotlintest.domain.commands

import com.ljuns.kotlintest.domain.datasource.ForecastProvider
import com.ljuns.kotlintest.domain.model.ForecastList

/**
 * Created by ljuns at 2018/11/29.
 * I am just a developer.
 * 介于 Activity 与请求层
 */

class RequestForecastCommand(private val zipCode: Long,
                             private val forecastProvider: ForecastProvider = ForecastProvider())
    : Command<ForecastList> {

    companion object {
        const val DAYS = 7
    }

    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}