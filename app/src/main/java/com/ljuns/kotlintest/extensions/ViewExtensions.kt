package com.ljuns.kotlintest.extensions

import android.widget.TextView

/**
 * Created by ljuns at 2018/12/5.
 * I am just a developer.
 * View 的拓展
 */

/**
 * TextView 的拓展属性
 */
var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)