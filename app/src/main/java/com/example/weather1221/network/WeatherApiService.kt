package com.example.weather1221.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.weatherapi.com/"
private const val CITY = "Nizhny Novgorod"
private const val API_KEY = "c5ac98eb1ea8423096983644231708"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    //реквест прогноза
    @GET("v1/forecast.json?q=$CITY&days=5&key=$API_KEY")
    suspend fun getForecast(): WeatherResponse
}

//синглтон для ретрофита
object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}