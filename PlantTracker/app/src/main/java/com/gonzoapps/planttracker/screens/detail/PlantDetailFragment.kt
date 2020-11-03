package com.gonzoapps.planttracker.screens.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.newPlantAdded.observe(viewLifecycleOwner, Observer { newPlantAdded ->
            if (newPlantAdded) {
                navigateToList(view)
            }
        })

        binding.buttonCancel.setOnClickListener {
            viewModel.onCancelClicked()
            navigateToList(it)
        }

        return binding.root
    }

    private fun navigateToList(view: View?) {
        view?.findNavController()?.navigate(R.id.action_plantDetailFragment_to_plantListFragment)
    }

    // TODO: Implement Hide Keyboard Function
    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}