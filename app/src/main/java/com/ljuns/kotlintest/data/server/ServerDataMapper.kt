package com.ljuns.kotlintest.data.server

import com.ljuns.kotlintest.domain.model.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import com.ljuns.kotlintest.domain.model.Forecast as ModelForecast

/**
 * Created by ljuns at 2018/12/4.
 * I am just a developer.
 * 将网络返回的 ForecastResult 转换成 ForecastList
 */

class ServerDataMapper {
    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(dt, weather[0].description, temp.max.toInt(), temp.min.toInt(), generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}