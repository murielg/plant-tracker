package com.gonzoapps.planttracker.screens.myplants

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.gonzoapps.planttracker.R
import com.gonzoapps.planttracker.databinding.FragmentMyPlantsBinding


class MyPlantsFragment : Fragment() {

    private lateinit var binding: FragmentMyPlantsBinding

    private val viewModel : PlantsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_plants, container, false)

        binding.myPlantsViewModel = viewModel

        binding.lifecycleOwner = this

        val adapter = MyPlantsAdapter()

        binding.recyclerviewPlantList.adapter = adapter

        viewModel.allPlants.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.floatingActionButton.setOnClickListener {
            viewModel.onAddNewPlantButtonClicked()
            it.findNavController().navigate(R.id.action_plantListFragment_to_plantDetailFragment)
        }

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