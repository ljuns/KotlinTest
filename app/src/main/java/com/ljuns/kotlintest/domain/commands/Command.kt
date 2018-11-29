package com.ljuns.kotlintest.domain.commands

/**
 * Created by ljuns at 2018/11/29.
 * I am just a developer.
 */

interface Command<T>{
    fun execute(): T
}