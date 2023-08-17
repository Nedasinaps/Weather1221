package com.example.weather1221

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.weather1221.adapter.WeatherAdapter
import com.example.weather1221.databinding.ActivityMainBinding
import com.example.weather1221.model.WeatherViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //listener для свайп-рефрешера
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getDaysList()
        }

        //adapter для recyclera
        val adapter = WeatherAdapter(this)
        binding.recyclerView.adapter = adapter

        //отслеживание изменения состояния переменной status во ViewModel
        viewModel.status.observe(this) { newStatus ->
            binding.statusText.text = newStatus.toString()
        }

        //отслеживание изменения списка daysList с прогнозами на 5 дней во ViewModel
        viewModel.daysList.observe(this) {
            it?.let {
                adapter.refreshDaysList(it)
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }
}