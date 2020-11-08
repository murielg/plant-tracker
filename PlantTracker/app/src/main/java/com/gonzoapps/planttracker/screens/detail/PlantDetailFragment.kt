package com.gonzoapps.planttracker.screens.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gonzoapps.planttracker.R
import com.gonzoapps.planttracker.databinding.FragmentPlantDetailBinding
import com.gonzoapps.planttracker.util.getViewModelFactory
import com.google.android.material.snackbar.Snackbar

class PlantDetailFragment : Fragment() {

    private lateinit var binding: FragmentPlantDetailBinding

    private val args: PlantDetailFragmentArgs by navArgs()

    private val viewModel by viewModels<PlantDetailViewModel> {getViewModelFactory()  }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_plant_detail,
            container,
            false
        )

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        binding.buttonCancel.setOnClickListener {
            viewModel.onCancelClicked()
        }

        viewModel.navigateToPlantList.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    this.findNavController().navigate(PlantDetailFragmentDirections.actionPlantDetailFragmentToPlantListFragment())
                    hideKeyboard()
                }
            }
        })


        viewModel.snackbarText.observe(viewLifecycleOwner, Observer {
            showSnackbar(it)
        })

        return binding.root
    }

    private fun showSnackbar(snackbarText: String) {
        Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                snackbarText,
                Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.start(args.plantId)
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}