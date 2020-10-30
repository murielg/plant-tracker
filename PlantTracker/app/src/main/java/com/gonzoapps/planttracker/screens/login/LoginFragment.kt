package com.gonzoapps.planttracker.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.gonzoapps.planttracker.R
import com.gonzoapps.planttracker.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Hide ActionBar in LoginFragment
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.buttonCreateaccount.setOnClickListener {
            it.findNavController().navigate(R.id.action_login_to_welcomeFragment)
        }

        binding.buttonLogin.setOnClickListener {
            it.findNavController().navigate(R.id.action_login_to_welcomeFragment)
        }

        return binding.root
    }
}