package com.ljuns.kotlintest.ui.adapters

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ljuns.kotlintest.domain.model.ForecastList

/**
 * @author ljuns
 * Created at 2018/11/28.
 */
class ForecastListAdapter(private val weekForecast: ForecastList) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // with() {} 意思是把（）里面的内容当作一个对象，然后在 {} 中使用这个对象
        with(weekForecast.dailyForecast[position]) {
            holder.textView.text = "$date - $description - $high/$low"
        }

        /**
         * 上面的 with 等同于如下：
         * val daily = weekForecast.dailyForecast[position]
         * holder.textView.text = "$daily.date - $daily.description - $daily.high/$daily.low"
         * 或者使用 this 表示当前对象：holder.textView.text = "$this.date - $this.description - $this.high/$this.low"
         */
    }

    override fun getItemCount(): Int = weekForecast.dailyForecast.size

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}
