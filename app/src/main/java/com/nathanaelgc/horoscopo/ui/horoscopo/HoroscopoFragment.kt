package com.nathanaelgc.horoscopo.ui.horoscopo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nathanaelgc.horoscopo.databinding.FragmentHoroscopoBinding


class HoroscopoFragment : Fragment() {
    private var _binding: FragmentHoroscopoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopoBinding.inflate(inflater, container, false)
        return binding.root

    }

}