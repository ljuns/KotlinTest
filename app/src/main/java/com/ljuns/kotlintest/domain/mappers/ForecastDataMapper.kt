package com.ljuns.kotlintest.domain.mappers

import com.ljuns.kotlintest.data.server.Forecast
import com.ljuns.kotlintest.data.server.ForecastResult
import com.ljuns.kotlintest.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import com.ljuns.kotlintest.domain.model.Forecast as ModelForecast

/**
 * Created by ljuns at 2018/11/29.
 * I am just a developer.
 * 把后台返回的数据类型转换为实际需要使用的
 */

class ForecastDataMapper {
    fun convertFromDataModel(forecast: ForecastResult): ForecastList = with(forecast) {
        ForecastList(city.id, city.name, city.country, convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        // list.map {} 意思是循环遍历 list 并生成一个新的 list，并且使用 it 表示原集合的元素
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertData(forecast.dt),
            forecast.weather[0].description, forecast.temp.max.toInt(),
            forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"

    private fun convertData(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }
}