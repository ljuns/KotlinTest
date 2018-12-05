package com.ljuns.kotlintest.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.ljuns.kotlintest.R
import com.ljuns.kotlintest.domain.commands.RequestDayForecastCommand
import com.ljuns.kotlintest.domain.model.Forecast
import com.ljuns.kotlintest.extensions.color
import com.ljuns.kotlintest.extensions.textColor
import com.ljuns.kotlintest.extensions.toDateString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.text.DateFormat

/**
 * Created by ljuns at 2018/12/5.
 * I am just a developer.
 * 天气详情
 */

class DetailActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initToolbar()
        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }

        async {
            // 根据 ID 请求数据
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()

            // 更新 UI
            uiThread {
                bindForecast(result)
            }
        }
    }

    /**
     * 绑定天气
     */
    private fun bindForecast(forecast: Forecast) {
        with(forecast) {
            supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)

            Picasso.get().load(iconUrl).into(icon)
            weatherDescription.text = description
            bindWeather(high to maxTemperature, low to minTemperature)
        }
    }

    /**
     * 绑定温度
     */
    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        // Pair<Int, TextView>：first 表示第一个类型，second 表示第二个类型
        it.second.text = "${it.first}º"

        // 不同的温度显示不同的颜色
        it.second.textColor = color(when(it.first){
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}
