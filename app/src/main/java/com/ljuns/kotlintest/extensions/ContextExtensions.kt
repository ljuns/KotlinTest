package com.ljuns.kotlintest.extensions

import android.content.Context
import androidx.core.content.ContextCompat

/**
 * Created by ljuns at 2018/12/5.
 * I am just a developer.
 * Context 的拓展
 */

/**
 * 拓展一个 color() 函数
 */
fun Context.color(res: Int) = ContextCompat.getColor(this, res)