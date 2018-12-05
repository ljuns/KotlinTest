package com.ljuns.kotlintest.ui.activities

import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.ljuns.kotlintest.R
import com.ljuns.kotlintest.ui.App
import org.jetbrains.anko.toast

/**
 * Created by ljuns at 2018/12/5.
 * I am just a developer.
 */

interface ToolbarManager {
    val toolbar: Toolbar

    /**
     * 标题
     */
    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    /**
     * 初始化
     */
    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> App.instance.toast("Setting")
                else -> App.instance.toast("Unknown option")

            }
            true
        }
    }

    /**
     * 返回
     */
    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener {
            up()
        }
    }

    /**
     * 返回图标
     */
    private fun createUpDrawable() = with(DrawerArrowDrawable(toolbar.context)) {
        progress = 1f
        this
    }

    /**
     * 跟随 RecyclerView 的滑动
     */
    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }

    /**
     * 进入
     */
    fun Toolbar.slideEnter() {
        if (translationY < 0f) animate().translationY(0f)
    }

    /**
     * 退出
     */
    fun Toolbar.slideExit() {
        if (translationY == 0f) animate().translationY(-height.toFloat())
    }

}
