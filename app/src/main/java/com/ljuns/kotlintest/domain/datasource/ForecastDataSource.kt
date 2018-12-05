package com.ljuns.kotlintest.domain.datasource

import com.ljuns.kotlintest.domain.model.Forecast
import com.ljuns.kotlintest.domain.model.ForecastList

/**
 * Created by ljuns at 2018/12/4.
 * I am just a developer.
 * 请求数据的接口，包括从数据库请求和从网络请求
 */

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}