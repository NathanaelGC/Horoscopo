package com.nathanaelgc.horoscopo.domain

import com.nathanaelgc.horoscopo.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign: String): PredictionModel?
}