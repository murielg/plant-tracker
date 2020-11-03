package com.gonzoapps.planttracker.screens.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.gonzoapps.planttracker.R
import com.gonzoapps.planttracker.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {

    private lateinit var binding : FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)

        binding.buttonInstructionsContinue.setOnClickListener{
            it.findNavController().navigate(R.id.action_instructionsFragment_to_plantListFragment)
        }

        return binding.root
    }


}