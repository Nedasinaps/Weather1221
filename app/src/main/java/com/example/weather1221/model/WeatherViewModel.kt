package com.example.weather1221.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather1221.network.Day
import com.example.weather1221.network.WeatherApi
import kotlinx.coroutines.launch

//enum с вариантами значений для переменной status
enum class WeatherApiStatus { LOADING, ERROR, DONE }

class WeatherViewModel : ViewModel() {
    //список с прогнозами
    private val _daysList = MutableLiveData<List<Day>>()
    val daysList: LiveData<List<Day>> = _daysList

    //служебная переменная, для отслеживания загрузки/ошибок
    private val _status = MutableLiveData<WeatherApiStatus>()
    val status: LiveData<WeatherApiStatus> = _status

    init {
        getDaysList()
    }

    fun getDaysList() {
        //загружаем прогноз с помощью корутины
        viewModelScope.launch {
            _status.value = WeatherApiStatus.LOADING
            try {
                val response = WeatherApi.retrofitService.getForecast()
                _daysList.value = response.forecast.daysList
                _status.value = WeatherApiStatus.DONE
            } catch (e: Exception) {
                _status.value = WeatherApiStatus.ERROR
            }
        }
    }
}