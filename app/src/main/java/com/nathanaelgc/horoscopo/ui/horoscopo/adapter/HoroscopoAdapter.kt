package com.nathanaelgc.horoscopo.ui.horoscopo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nathanaelgc.horoscopo.R
import com.nathanaelgc.horoscopo.domain.model.HoroscopoInfo

class HoroscopoAdapter(
    private var horoscopoList: List<HoroscopoInfo> = emptyList(),
    private val onItemSelected: (HoroscopoInfo) -> Unit
) : RecyclerView.Adapter<HoroscopoViewHolder>() {

    fun updateList(list: List<HoroscopoInfo>) {
        horoscopoList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HoroscopoViewHolder {
        return HoroscopoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscopo, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: HoroscopoViewHolder,
        position: Int
    ) {
        holder.render(horoscopoList[position], onItemSelected)
    }

    override fun getItemCount() = horoscopoList.size
}