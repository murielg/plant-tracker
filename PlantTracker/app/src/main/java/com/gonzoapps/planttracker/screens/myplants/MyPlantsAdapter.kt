package com.gonzoapps.planttracker.screens.myplants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gonzoapps.planttracker.R
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val plantName : TextView = itemView.findViewById(R.id.textview_list_name)
        private val plantLocation : TextView = itemView.findViewById(R.id.textview_list_location)

        fun bind(item: Plant) {
            plantName.text = item.name
            plantLocation.text = item.location
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_plant_view, parent, false)
                return ViewHolder(view)
            }
        }

    }
}