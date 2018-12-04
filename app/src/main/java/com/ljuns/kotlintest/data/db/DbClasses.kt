package com.ljuns.kotlintest.data.db

import java.util.*

/**
 * Created by ljuns at 2018/12/3.
 * I am just a developer.
 * 将数据库表中存储的实体类型
 */

class CityForecast(val map: MutableMap<String, Any?>, val dailyForecast: List<DayForecast>) {
    // 把 map 中 key-value 映射给对应的属性，同时也会属性反向委托给 map
    var _id: Long by map
    var city: String by map
    var country: String by map

    constructor(id: Long, city: String, country: String, dailyForecast: List<DayForecast>)
            : this(HashMap(), dailyForecast) {
        this._id = id
        this.city = city
        this.country = country
    }
}

class DayForecast(var map: MutableMap<String, Any?>) {
    var _id: Long by map
    var date: Long by map
    var description: String by map
    var high: Int by map
    var low: Int by map
    var iconUrl: String by map
    var cityId: Long by map

    constructor(date: Long, description: String, high: Int, low: Int, iconUrl: String, cityId: Long)
            : this(HashMap()) {
        this.date = date
        this.description = description
        this.high = high
        this.low = low
        this.iconUrl = iconUrl
        this.cityId = cityId
    }
}

