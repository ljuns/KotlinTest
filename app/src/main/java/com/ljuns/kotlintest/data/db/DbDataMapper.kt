package com.ljuns.kotlintest.data.db

import com.ljuns.kotlintest.domain.model.Forecast
import com.ljuns.kotlintest.domain.model.ForecastList

/**
 * Created by ljuns at 2018/12/4.
 * I am just a developer.
 */

class DbDataMapper {

    /**
     * 将 CityForecast 转换成 ForecastList
     */
    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        // .map { } 表示转换成一个 list，元素就是 {} 的内容
        // it 就是 dailyForecast 的元素
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    /**
     * 将 DayForecast 转换成 Forecast
     */
    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(_id, date, description, high, low, iconUrl)
    }

    /**
     * 将 ForecastList 转换成 CityForecast
     */
    fun convertFromDomain(forecastList: ForecastList) = with(forecastList) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    /**
     * 将 Forecast 转换成 DayForecast
     */
    private fun convertDayFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }
}