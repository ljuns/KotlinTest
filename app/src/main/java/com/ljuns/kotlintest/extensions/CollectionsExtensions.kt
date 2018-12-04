package com.ljuns.kotlintest.extensions

/**
 * Created by ljuns at 2018/12/4.
 * I am just a developer.
 * 将 map 转换成 Pair
 */

fun <K, V : Any> MutableMap<K, V?>.toVarargArray():
        // map { } 是转换成一个 list，toTypedArray() 是转换成一个 array
        Array<out Pair<K, V>> =  map { Pair(it.key, it.value!!) }.toTypedArray()