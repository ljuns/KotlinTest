package com.ljuns.kotlintest.extensions

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