package com.ljuns.kotlintest.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.ljuns.kotlintest.ui.APP
import org.jetbrains.anko.db.*

/**
 * Created by ljuns at 2018/12/3.
 * I am just a developer.
 * 数据库管理（创建、升级）
 */

class ForecastDbHelper(ctx: Context = APP.instance) :
    ManagedSQLiteOpenHelper(ctx, ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {

    /**
     * 静态内部类
     */
    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance: ForecastDbHelper by lazy { ForecastDbHelper() }
    }

    /**
     * 创建数据库
     */
    override fun onCreate(db: SQLiteDatabase?) {
        // 第二个参数 true 表示创建之前检查该表是否存在，false 就直接创建
        db?.createTable(CityForecastTable.NAME, true,
            CityForecastTable.ID to INTEGER + PRIMARY_KEY,
            CityForecastTable.CITY to TEXT,
            CityForecastTable.COUNTRY to TEXT)

        db?.createTable(DayForecastTable.NAME, true,
            DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            DayForecastTable.DATE to INTEGER,
            DayForecastTable.DESCRIPTION to TEXT,
            DayForecastTable.HIGH to INTEGER,
            DayForecastTable.LOW to INTEGER,
            DayForecastTable.ICON_URL to TEXT,
            DayForecastTable.CITY_ID to INTEGER)
    }

    /**
     * 升级数据库
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // 目前升级数据库版本时会直接删除原有的表
        db?.dropTable(CityForecastTable.NAME, true)
        db?.dropTable(DayForecastTable.NAME, true)

        onCreate(db)
    }
}