package com.nathanaelgc.horoscopo.data.providers

import com.nathanaelgc.horoscopo.domain.model.HoroscopoInfo
import com.nathanaelgc.horoscopo.domain.model.HoroscopoInfo.*
import javax.inject.Inject

class HoroscopoProvider @Inject constructor() {
    fun getHoroscopo(): List<HoroscopoInfo> {
        return listOf(
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
            Pisces
        )
    }
}