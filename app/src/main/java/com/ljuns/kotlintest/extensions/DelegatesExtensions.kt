package com.ljuns.kotlintest.extensions

import android.content.Context
import android.preference.Preference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by ljuns at 2018/12/2.
 * I am just a developer.
 */


/**
 * 自定义的属性委托类
 * 获取属性值时，如果为 null 就抛出异常，否则返回属性值
 * 设置属性值时，如果已初始化（不为 null）抛出异常，否则设置属性值
 */
class NotNullSingleValueVar<T> : ReadWriteProperty<Any, T> {

    private var value: T? = null

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} not initialized")
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        if (this.value != null) {
            throw IllegalStateException("${property.name} not initialized")
        } else {
            this.value = value
        }
    }
}

/**
 * 自定义的委托类，用来操作 SharedPreferences
 */
class Preference<T>(private val context: Context, private val name: String, private val defaultValue: T) :
    ReadWriteProperty<Any?, T> {

    private val pref by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T = findPreference(name, defaultValue)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    private fun findPreference(key: String, default: T): T = with(pref) {
        val res = when (default) {
            is Int -> getInt(key, default)
            is Long -> getLong(key, default)
            is Float -> getFloat(key, default)
            is Boolean -> getBoolean(key, default)
            is String -> getString(key, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        // 强转
        res as T
    }

    private fun putPreference(key: String, value: T) = with(pref.edit()) {
        when (value) {
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            is Boolean -> putBoolean(key, value)
            is String -> putString(key, value)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }.apply()
    }

}

/**
 * 使用单例再封装一层
 */
object DelegatesExt {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
    fun <T> longPreference(context: Context, name: String, defaultValue: T) = Preference(context, name, defaultValue)
}