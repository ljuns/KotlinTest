package com.ljuns.kotlintest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ljuns.kotlintest.R
import com.ljuns.kotlintest.R.id.maxTemperature
import com.ljuns.kotlintest.R.id.minTemperature
import com.ljuns.kotlintest.domain.model.Forecast
import com.ljuns.kotlintest.domain.model.ForecastList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * @author ljuns
 * Created at 2018/11/28.
 */
class ForecastListAdapter(private val weekForecast: ForecastList) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    // OnItemClickListener? 表示 mOnItemClickListener 可为 null
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mItemClickListener: ((Int, Forecast) -> Unit)? = null
    private var mClickListener: ((Forecast) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {

            mOnItemClickListener?.onItemClick(holder.adapterPosition, weekForecast[holder.adapterPosition])

            // lambda
            mClickListener?.invoke(weekForecast[holder.adapterPosition])
            mItemClickListener?.invoke(holder.adapterPosition, weekForecast[holder.adapterPosition])
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 使用了操作符重载，weekForecast[position] 实际会调用 weekForecast.get(position)
        /*with(weekForecast[position]) {
            holder.textView.text = "$date - $description - $high/$low"
        }*/

        // with() {} 意思是把（）里面的内容当作一个对象，然后在 {} 中使用这个对象
        /*with(weekForecast.dailyForecast[position]) {
            holder.textView.text = "$date - $description - $high/$low"
        }*/

        /**
         * 上面的 with 等同于如下：
         * val daily = weekForecast.dailyForecast[position]
         * holder.textView.text = "$daily.date - $daily.description - $daily.high/$daily.low"
         * 或者使用 this 表示当前对象：holder.textView.text = "$this.date - $this.description - $this.high/$this.low"
         */

        holder.bind(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size()

    /**
     * ViewHolder
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(forecast: Forecast) {
            with(forecast) {
                Picasso.get().load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemperature.text = maxTemperature.toString()
                itemView.minTemperature.text = minTemperature.toString()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, forecast: Forecast)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mOnItemClickListener = listener
    }

    /**
     * 使用 lambda 表达式
     */
    fun setOnItemClickListener(listener: (Forecast) -> Unit) {
        mClickListener = listener
    }

    fun setOnItemClickListener(listener: (Int, Forecast) -> Unit) {
        mItemClickListener = listener
    }

}
