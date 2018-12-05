package com.ljuns.kotlintest.data.server

import com.ljuns.kotlintest.data.db.ForecastDb
import com.ljuns.kotlintest.domain.datasource.ForecastDataSource
import com.ljuns.kotlintest.domain.model.Forecast
import com.ljuns.kotlintest.domain.model.ForecastList

/**
 * Created by ljuns at 2018/12/4.
 * I am just a developer.
 * 从网络请求数据
 */

/**
 * 从网络请求数据，返回后保存到数据库，然后再从数据库查询返回
 */
class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    /**
     * 该请求只查询数据库
     */
    override fun requestDayForecast(id: Long): Forecast? = throw UnsupportedOperationException()

    /**
     * 根据 zipCode 和 date 请求网络
     */
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        // 网络请求
        val result = ForecastByZipCodeRequest(zipCode.toString()).execute()
        // 将 ForecastResult 转换为 ForecastList
        val converted = dataMapper.convertToDomain(zipCode, result)
        // 存储到数据库
        forecastDb.saveForecast(converted)
        // 再从数据库取出
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}