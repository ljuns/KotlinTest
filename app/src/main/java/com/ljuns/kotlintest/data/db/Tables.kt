package com.ljuns.kotlintest.data.db

/**
 * Created by ljuns at 2018/12/3.
 * I am just a developer.
 * 数据库表对应的字段
 */

/**
 * 使用对象声明的方式，即创建类的同时创建对象，通常用作懒汉式单例
 */

object CityForecastTable {
    val NAME = "CityForecast"
    val ID = "_id"
    val CITY = "city"
    val COUNTRY = "country"
}

/*public final class CityForecastTable {

    private static final String NAME = "CityForecast;
    private static final String ID = "_id";
    private static final String CITY = "city";
    private static final String COUNTRY = "country";
    public static final CityForecastTable INSTANCE;

    public static String getNAME() {
        return NAME;
    }

    public static int getID() {
        return ID;
    }

    public static String getCITY() {
        return CITY;
    }

    public static String getCOUNTRY() {
        return COUNTRY;
    }

    static {
        CityForecastTable var0 = new CityForecastTable();
        INSTANCE = var0;
        NAME = "CityForecast";
        ID = "_id";
        CITY = "city";
        COUNTRY = "country";
    }
}*/

object DayForecastTable {
    val NAME = "DayForecast"
    val ID = "_id"
    val DATE = "date"
    val DESCRIPTION = "description"
    val HIGH = "high"
    val LOW = "low"
    val ICON_URL = "iconUrl"
    val CITY_ID = "cityId"
}