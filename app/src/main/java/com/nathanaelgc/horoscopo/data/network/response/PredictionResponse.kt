package com.nathanaelgc.horoscopo.data.network.response

import com.google.gson.annotations.SerializedName
import com.nathanaelgc.horoscopo.domain.model.PredictionModel

data class PredictionResponse(
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscopo: String,
    @SerializedName("sign") val sign: String
) {

    fun toDomain(): PredictionModel {
        return PredictionModel(horoscopo = horoscopo, sign = sign)
    }
}