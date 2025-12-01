package com.nathanaelgc.horoscopo.ui.horoscopo

import com.nathanaelgc.horoscopo.data.providers.HoroscopoProvider
import com.nathanaelgc.horoscopo.motherobject.HoroscopoMotherObject.horoscopoInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class HoroscopoViewModelTest {
    @MockK
    lateinit var horoscopoProvider: HoroscopoProvider

    private lateinit var viewModel: HoroscopoViewModel

    @Before
    fun setUp(){
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `when viewModel is created then horoscopos are loaded`(){
        every { horoscopoProvider.getHoroscopo() } returns horoscopoInfoList
        viewModel= HoroscopoViewModel(horoscopoProvider)

        val horoscopo = viewModel.horoscopo.value

        assertTrue(horoscopo.isNotEmpty())
    }
}