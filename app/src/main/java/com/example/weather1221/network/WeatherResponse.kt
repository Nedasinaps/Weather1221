package com.example.weather1221.network

import com.squareup.moshi.Json

/**
 * классы для парсинга json-а полученного от сервера
 */
data class WeatherResponse(
    @Json(name = "forecast")
    val forecast: Forecast
)

data class Forecast(
    @Json(name = "forecastday")
    val daysList: List<Day>
)

data class Day(
    val date: String,
    @Json(name = "day")
    val dayWeather: DayWeather
)

data class DayWeather(
    @Json(name = "avgtemp_c")
    val avgTemp: Double,
    @Json(name = "maxwind_kph")
    val maxWind: Double,
    @Json(name = "avghumidity")
    val avgHumid: Double,
    @Json(name = "condition")
    val condition: Condition
)

data class Condition(
    val text: String,
    val icon: String
)