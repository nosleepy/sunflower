package com.grandstream.sunflower.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grandstream.sunflower.databinding.ListItemPlantBinding
import com.grandstream.sunflower.entity.Plant

class PlantAdapter(private val list: List<Plant>): RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(ListItemPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val image = holder.binding.image
        val name = holder.binding.name
        name.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class PlantViewHolder(
        val binding: ListItemPlantBinding
    ): RecyclerView.ViewHolder(binding.root) {
        init {

        }
    }
}