package com.ljuns.kotlintest.domain.commands

import com.ljuns.kotlintest.data.server.ForecastRequest
import com.ljuns.kotlintest.domain.mappers.ForecastDataMapper
import com.ljuns.kotlintest.domain.model.ForecastList

/**
 * Created by ljuns at 2018/11/29.
 * I am just a developer.
 */

class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        // 将 ForecastResult 转换为 ForecastList
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }

}