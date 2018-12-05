package com.ljuns.kotlintest.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by ljuns at 2018/12/5.
 * I am just a developer.
 */

fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}