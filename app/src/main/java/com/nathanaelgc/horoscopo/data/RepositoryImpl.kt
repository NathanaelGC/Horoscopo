package com.nathanaelgc.horoscopo.data

import android.util.Log
import com.nathanaelgc.horoscopo.data.network.HoroscopoApiService
import com.nathanaelgc.horoscopo.domain.Repository
import com.nathanaelgc.horoscopo.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopoApiService) : Repository {

    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { apiService.getHoroscopo(sign)  }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("Error", "Ha ocurrido un error ${it.message}") }
        return null
    }
}