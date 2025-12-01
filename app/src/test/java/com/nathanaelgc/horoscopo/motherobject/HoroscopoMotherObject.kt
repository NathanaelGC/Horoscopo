package com.nathanaelgc.horoscopo.motherobject

import com.nathanaelgc.horoscopo.data.network.response.PredictionResponse
import com.nathanaelgc.horoscopo.domain.model.HoroscopoInfo.*

object HoroscopoMotherObject {

    val anyResponse = PredictionResponse("date", "prediction", "taurus")

    val horoscopoInfoList = listOf(
    Aries,
    Taurus,
    Gemini,
    Cancer,
    Leo,
    Virgo,
    Libra,
    Scorpio,
    Sagittarius,
    Capricorn,
    Aquarius,
    Pisces)
}