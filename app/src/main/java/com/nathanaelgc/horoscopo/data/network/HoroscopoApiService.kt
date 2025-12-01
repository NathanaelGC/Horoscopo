package com.nathanaelgc.horoscopo.data.network

import com.nathanaelgc.horoscopo.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopoApiService {
    @GET("/{sign}")
    suspend fun getHoroscopo(@Path("sign") sign:String): PredictionResponse
}