package com.gonzoapps.planttracker.screens.myplants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gonzoapps.planttracker.R
import com.gonzoapps.planttracker.databinding.ItemPlantViewBinding
import com.gonzoapps.planttracker.models.Plant

class MyPlantsAdapter : RecyclerView.Adapter<MyPlantsAdapter.ViewHolder>() {

    var data = listOf<Plant>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    class ViewHolder(val binding: ItemPlantViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Plant) {
            with(binding) {
                binding.plant = item
                binding.executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPlantViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }
}