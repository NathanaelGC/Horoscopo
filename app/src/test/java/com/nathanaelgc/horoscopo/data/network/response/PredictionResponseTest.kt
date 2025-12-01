package com.nathanaelgc.horoscopo.data.network.response

import com.nathanaelgc.horoscopo.motherobject.HoroscopoMotherObject.anyResponse
import io.kotlintest.shouldBe
import org.junit.Test

class PredictionResponseTest {

    @Test
    fun `toDomain should return a correct predictionModel`(){
        //Given
        val horoscopoResponse = anyResponse

        //When
        val predictionModel = horoscopoResponse.toDomain()

        //Then
        predictionModel.sign shouldBe  horoscopoResponse.sign
        predictionModel.horoscopo shouldBe horoscopoResponse.horoscopo
    }
}