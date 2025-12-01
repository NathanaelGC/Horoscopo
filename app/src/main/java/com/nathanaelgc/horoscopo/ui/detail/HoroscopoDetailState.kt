package com.nathanaelgc.horoscopo.ui.detail

import com.nathanaelgc.horoscopo.domain.model.HoroscopoModel

sealed class HoroscopoDetailState {
    data object Loading : HoroscopoDetailState()
    data class Error(val error: String) : HoroscopoDetailState()
    data class Success(val prediction: String, val sign: String, val horoscopoModel: HoroscopoModel) : HoroscopoDetailState()
}