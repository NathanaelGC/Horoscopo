package com.nathanaelgc.horoscopo.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nathanaelgc.horoscopo.domain.model.HoroscopoModel
import com.nathanaelgc.horoscopo.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopoDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase): ViewModel(){
    private var _state = MutableStateFlow<HoroscopoDetailState>(HoroscopoDetailState.Loading)
    val state: StateFlow<HoroscopoDetailState> = _state

    lateinit var horoscopo: HoroscopoModel

    fun getHoroscopo(sign: HoroscopoModel){
        horoscopo = sign
        viewModelScope.launch {
            _state.value = HoroscopoDetailState.Loading
            val result = withContext(Dispatchers.IO) { getPredictionUseCase(sign.name) }
            if(result != null) {
                _state.value = HoroscopoDetailState.Success(result.horoscopo, result.sign, horoscopo)
            }else{
                _state.value = HoroscopoDetailState.Error("Ha ocurrido un error, intentelo mas tarde")
            }
        }
    }
}