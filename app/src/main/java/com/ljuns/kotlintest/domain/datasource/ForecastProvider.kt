package com.ljuns.kotlintest.domain.datasource

import com.ljuns.kotlintest.data.db.ForecastDb
import com.ljuns.kotlintest.data.server.ForecastServer
import com.ljuns.kotlintest.domain.model.ForecastList
import com.ljuns.kotlintest.extensions.firstResult

/**
 * Created by ljuns at 2018/12/4.
 * I am just a developer.
 */

class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        // 数据源分别是数据库和网络
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    /**
     * 优先从数据库获取数据，然后再从网络获取
     */
    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = sources.firstResult {
        // 此处的 it 只能是 ForecastDb 或 ForecastServer
        requestSource(it, zipCode, days)
    }

    /**
     * 查询数据
     */
    private fun requestSource(source: ForecastDataSource, zipCode: Long, days: Int): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.size() >= days) res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}
