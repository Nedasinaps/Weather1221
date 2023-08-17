package com.example.weather1221.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weather1221.R
import com.example.weather1221.databinding.ListItemBinding
import com.example.weather1221.network.Day
import java.text.SimpleDateFormat
import java.util.Locale


class WeatherAdapter(private val context: Context) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var daysList: List<Day> = ArrayList()

    fun refreshDaysList(daysList: List<Day>) {
        this.daysList = daysList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return daysList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val day = daysList[position]
        holder.binding.itemDate.text = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale.ENGLISH
        ).format(SimpleDateFormat("yyyy-MM-dd").parse(day.date))

        holder.binding.itemCondition.text = day.dayWeather.condition.text
        holder.binding.itemAvgTemp.text =
            context.getString(R.string.avg_temp, day.dayWeather.avgTemp.toString())
        holder.binding.itemAvgHum.text =
            context.getString(R.string.avg_hum, day.dayWeather.avgHumid.toString())
        holder.binding.itemMaxWind.text =
            context.getString(R.string.max_wind, day.dayWeather.maxWind.toString())

        //url для картинки и загрузка картинки с помощью Coil
        val imgUri = day.dayWeather.condition.icon.toUri().buildUpon().scheme("https").build()
        holder.binding.itemImage.load(imgUri)
    }

    class WeatherViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
