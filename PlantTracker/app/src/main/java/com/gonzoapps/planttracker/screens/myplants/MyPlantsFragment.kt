package com.gonzoapps.planttracker.screens.myplants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gonzoapps.planttracker.R
import com.gonzoapps.planttracker.databinding.FragmentMyPlantsBinding
import com.gonzoapps.planttracker.models.Plant


class MyPlantsFragment : Fragment() {

    private lateinit var binding: FragmentMyPlantsBinding

    private lateinit var plants : List<String>

    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_plants, container, false)

        val adapter = PlantListAdapter()

        binding.recyclerviewPlantList.adapter = adapter

        plants = listOf(
            "Montsera",
            "Cactus",
            "Basil"
        )

        adapter.data = plants



        return binding.root
    }


}