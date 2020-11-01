package com.gonzoapps.planttracker.screens.myplants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.gonzoapps.planttracker.R
import com.gonzoapps.planttracker.databinding.FragmentMyPlantsBinding
import com.gonzoapps.planttracker.models.Plant


class MyPlantsFragment : Fragment() {

    private lateinit var binding: FragmentMyPlantsBinding

    private lateinit var viewModel : MyPlantsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_plants, container, false)

        viewModel = ViewModelProvider(this).get(MyPlantsViewModel::class.java)

        binding.myPlantsViewModel = viewModel

        binding.lifecycleOwner = this

        val adapter = MyPlantsAdapter()

        binding.recyclerviewPlantList.adapter = adapter

        viewModel.plants.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })


        setHasOptionsMenu(true)

        return binding.root
    }


}