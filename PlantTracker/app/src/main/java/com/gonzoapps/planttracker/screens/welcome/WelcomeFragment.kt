package com.gonzoapps.planttracker.screens.welcome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gonzoapps.planttracker.R
import com.gonzoapps.planttracker.databinding.FragmentWelcomeBinding
import timber.log.Timber
import kotlin.random.Random


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

        binding.textView4.text = getRandomPun()


        return binding.root
    }

    private fun getRandomPun() : String {
        val puns: Array<String> = resources.getStringArray(R.array.welcome_strings)
        return puns.random()
    }
}