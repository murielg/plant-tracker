package com.gonzoapps.planttracker.screens.myplants

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.gonzoapps.planttracker.R
import com.gonzoapps.planttracker.databinding.FragmentMyPlantsBinding
import com.gonzoapps.planttracker.util.getViewModelFactory


class MyPlantsFragment : Fragment() {

    private lateinit var binding: FragmentMyPlantsBinding

    private val viewModel by viewModels<PlantsViewModel> {getViewModelFactory()  }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_plants, container, false)

        binding.myPlantsViewModel = viewModel

        binding.lifecycleOwner = this

        val adapter = MyPlantsAdapter(PlantClickListener{
            plantId -> viewModel.onPlantClicked(plantId)
        })

        binding.recyclerviewPlantList.adapter = adapter

        binding.buttonPlantsNew.setOnClickListener {
            this.findNavController().navigate(MyPlantsFragmentDirections.actionPlantListFragmentToPlantDetailFragment(null))
            viewModel.onPlantDetailNavigated()
        }

        viewModel.allPlants.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToPlantDetail.observe(viewLifecycleOwner, Observer{
            plantId -> plantId?.let {
                this.findNavController().navigate(MyPlantsFragmentDirections
                        .actionPlantListFragmentToPlantDetailFragment(plantId))
                viewModel.onPlantDetailNavigated()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}