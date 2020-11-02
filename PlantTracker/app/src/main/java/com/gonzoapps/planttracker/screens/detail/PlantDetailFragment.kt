package com.gonzoapps.planttracker.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.gonzoapps.planttracker.R
import com.gonzoapps.planttracker.databinding.FragmentPlantDetailBinding
import com.gonzoapps.planttracker.models.Plant
import com.gonzoapps.planttracker.screens.myplants.PlantsViewModel

class PlantDetailFragment : Fragment() {

    private lateinit var binding: FragmentPlantDetailBinding

    private val viewModel: PlantsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_plant_detail,
            container,
            false
        )

        binding.myPlantsViewModel = viewModel

        binding.lifecycleOwner = this

        binding.buttonSave.setOnClickListener{
            val plantName = binding.edittextName.text.toString()

            //TODO: hide keyboard
            val newPlant  = Plant(plantName)
            viewModel.addNewPlant(newPlant)
            it.findNavController().navigate(R.id.action_plantDetailFragment_to_plantListFragment)
        }




        return binding.root
    }

}