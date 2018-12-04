package com.ljuns.kotlintest.data.db

import com.ljuns.kotlintest.domain.datasource.ForecastDataSource
import com.ljuns.kotlintest.domain.model.ForecastList
import com.ljuns.kotlintest.extensions.clear
import com.ljuns.kotlintest.extensions.parseList
import com.ljuns.kotlintest.extensions.parseOpt
import com.ljuns.kotlintest.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by ljuns at 2018/12/3.
 * I am just a developer.
 * 关于数据库的操作
 */

class ForecastDb(private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 private val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {

    /**
     * 根据城市 ID 和日期查询天气
     */
    // forecastDbHelper.use {} 表示在 {} 中已经获取到了数据库操作对象 writableDatabase，可以直接进行数据库操作
    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"

        // 获取城市对应日期的天气
        val dailyForecast = select(DayForecastTable.NAME)
            .whereSimple(dailyRequest, zipCode.toString(), date.toString())
            // 遍历查询返回的 Cursor 并转换成对应的实体集合
            .parseList{ DayForecast(HashMap(it)) }

        // 获取城市
        val city = select(CityForecastTable.NAME)
            .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
            .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    /**
     * 保存
     */
    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {

        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            // 使用 * 表示这个 Array 会被分解为 vararg
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}