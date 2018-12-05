package com.ljuns.kotlintest.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.ljuns.kotlintest.R
import com.ljuns.kotlintest.domain.commands.RequestForecastCommand
import com.ljuns.kotlintest.domain.model.Forecast
import com.ljuns.kotlintest.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

/**
 * Created by ljuns at 2018/11/30.
 * I am just a developer.
 */
class MainActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        initToolbar()
        attachToScroll(forecastList)

        // 设置 LayoutManager
        forecastList.layoutManager = LinearLayoutManager(this)

        /**
         * 异步
         */
        async {
            val result = RequestForecastCommand(94043).execute()
            // 切换到 UI 线程
            uiThread {
                val adapter = ForecastListAdapter(result)
                forecastList.adapter = adapter

                /**
                 * 以下两种方式实现 item 的点击事件
                 */
                // 使用匿名内部类
                adapter.setOnItemClickListener(object : ForecastListAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int, forecast: Forecast) {
                        startActivity<DetailActivity>(DetailActivity.ID to forecast.id,
                            DetailActivity.CITY_NAME to result.city)
                    }
                })

                toolbarTitle = "${result.city} (${result.country})"

                // 使用 lambda 表达式
                /*adapter.setOnItemClickListener { forecast ->
                    toast("date = ${forecast.date}")
                }
                adapter.setOnItemClickListener { position, forecast ->
                    toast("position = $position, date = ${forecast.date}")
                }*/
            }
        }
    }
}
