package com.gonzoapps.planttracker.screens.myplants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
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

        viewModel.plants.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_plantListFragment_to_plantDetailFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }


}