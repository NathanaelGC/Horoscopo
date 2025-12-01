package com.nathanaelgc.horoscopo.ui.horoscopo.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.nathanaelgc.horoscopo.databinding.ItemHoroscopoBinding
import com.nathanaelgc.horoscopo.domain.model.HoroscopoInfo

class HoroscopoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscopoBinding.bind(view)

    fun render(horoscopoInfo: HoroscopoInfo, onItemSelected: (HoroscopoInfo) -> Unit) {
        val context = binding.tvTitle.context
        binding.ivHoroscopo.setImageResource(horoscopoInfo.img)
        binding.tvTitle.text = context.getString(horoscopoInfo.name)

        binding.parent.setOnClickListener {
            startRotationAnimation(binding.ivHoroscopo, newLambda = { onItemSelected(horoscopoInfo) })
           // onItemSelected(horoscopoInfo)
        }
    }

    private fun startRotationAnimation(view: View, newLambda: () -> Unit) {
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction { newLambda() }
            start()
        }
    }
}