package com.gonzoapps.planttracker.screens.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
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

    private lateinit var newPlant : Plant

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
            addNewPlant(it)
        }

        binding.buttonCancel.setOnClickListener {
            it.findNavController().navigate(R.id.action_plantDetailFragment_to_plantListFragment)
        }

        return binding.root
    }

    private fun addNewPlant(view: View?) {
        binding.apply {
            val plantName = edittextName.text.toString()
            val plantLocation = edittextLocation.text.toString()
            val plantCareInstructions = edittextInstructions.text.toString()
            val plantEventLog = edittextLog.text.toString()

            newPlant = Plant(
                plantName,
                plantLocation,
                plantCareInstructions,
                plantEventLog
            )
        }

        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)

        viewModel.addNewPlant(newPlant)

        //TODO: move to navigate function
        view?.findNavController()?.navigate(R.id.action_plantDetailFragment_to_plantListFragment)
    }



}