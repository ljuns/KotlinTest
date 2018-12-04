package com.ljuns.kotlintest.extensions

import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder

/**
 * Created by ljuns at 2018/12/3.
 * I am just a developer.
 * 针对数据库的拓展函数
 */

/**
 * 遍历 Cursor 并转换成对应的实体 DayForecastTable 集合
 */
fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T): List<T> =
        // parseList() 会遍历 Cursor，每一行都生成一个 map，map 的 key-value 就是每一列的名称和值
        // 最后返回一个 list
        parseList(object : MapRowParser<T> {
            // 将每一行生成的 columns 作为参数
            // parser(columns) 意思是执行外部实现的方法体
            override fun parseRow(columns: Map<String, Any>): T = parser(columns)
        })

/**
 * 遍历 Cursor 并转换成对应的实体 CityForecast 集合
 * parseOpt 和 parseList 的区别在于前者可以返回 null
 */
fun <T : Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T): T? =
        parseOpt(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any>): T = parser(columns)
        })

/**
 * 删除数据库表
 */
fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}
