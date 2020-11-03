package com.gonzoapps.planttracker.screens.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.gonzoapps.planttracker.R
import com.gonzoapps.planttracker.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome,
            container,
            false
        )

        binding.textviewWelcomeMessage.text = getRandomPun()

        binding.buttonWelcome.setOnClickListener{
            it.findNavController().navigate(R.id.action_welcomeFragment_to_instructionsFragment)
        }


        return binding.root
    }

    private fun getRandomPun() : String {
        val puns: Array<String> = resources.getStringArray(R.array.welcome_strings)
        return puns.random()
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}