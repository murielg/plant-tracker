package com.gonzoapps.planttracker.screens.myplants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gonzoapps.planttracker.databinding.ItemPlantViewBinding
import com.gonzoapps.planttracker.models.Plant

class MyPlantsAdapter(val clickListener: PlantClickListener) : ListAdapter<Plant, MyPlantsAdapter.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), clickListener)

    class ViewHolder(val binding: ItemPlantViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Plant, clickListener: PlantClickListener) {
            with(binding) {
                plant = item
                binding.executePendingBindings()
                binding.clickListener = clickListener
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
class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}

class PlantClickListener(val clickListener: (plantId: String)-> Unit) {
    fun onClick(plant: Plant) = clickListener(plant.plantId)
}